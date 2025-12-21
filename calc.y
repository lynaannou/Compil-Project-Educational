%{
    #include <stdio.h>
    #include <stdlib.h>
    #include <math.h>

    int yylex(void);
    int yyerror(const char *s);
    int syntax_ok = 1; /*this is to know if the syntax is correct or not*/
%}

%union {
    double val;
    struct {
        double somme;
        double produit;
        int nb;
    } list;
}

%token <val> N
%token SOMME PRODUIT MOYENNE
%token SIN COS TAN EXP LN SQRT
%token POW POW_OP

%right POW_OP


%type <val> e t p f
%type <list> liste

%%

input:
    e '\n'
    {
        if (syntax_ok) {
            printf("Expression correcte !\n");
            printf("Results = %f\n", $1);
            
        }
}
;

e: e '+' t {$$ = $1+$3;}
 | e '-' t  {$$ = $1-$3;}
 | t {$$ = $1;}
 ;

t: t '*' p {$$ = $1*$3;}
 | t '/' p { if ($3 == 0) {
             printf("Erreur, division par 0");
             syntax_ok = 0;
             $$ = 0;
             }else{
                $$ = $1 / $3;
             }                  
             }
 | p {$$ = $1;}
 ;


p:
      f
    | f POW_OP p { $$ = pow($1, $3); }
;


f: '(' e ')' {$$ = $2;}
 | '-' f    {$$ = -$2;}
 | N        {$$ = $1;}
 /* fonctions à N arguments */
    | SOMME '(' liste ')'    { $$ = $3.somme; }
    | PRODUIT '(' liste ')'  { $$ = $3.produit; }
    | MOYENNE '(' liste ')'  { $$ = $3.somme / $3.nb; }
    | SOMME '(' ')' {
    printf("Erreur sémantique : somme() nécessite au moins un argument\n");
    syntax_ok = 0;
    $$ = 0;
    }
    | PRODUIT '(' ')' {
        printf("Erreur sémantique : produit() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$ = 0;
    }
    | MOYENNE '(' ')' {
        printf("Erreur sémantique : moyenne() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$ = 0;
    }

    
    | POW '(' e ',' e ')'     { $$ = pow($3, $5); }
    | POW '(' ')' {
    printf("Erreur sémantique : puissance() nécessite deux arguments\n");
    syntax_ok = 0;
    $$ = 0;
    }
    | POW '(' e ')' {
    printf("Erreur sémantique : puissance(x) invalide, 2 arguments requis\n");
    syntax_ok = 0;
    $$ = 0;
    }
    | POW '(' e ',' e ',' e ')' {
    printf("Erreur sémantique : puissance(a,b) accepte exactement deux arguments\n");
    syntax_ok = 0;
    $$ = 0;
    }


    /* fonctions à 1 argument */
    | SIN '(' e ')'   { $$ = sin($3); }
    | COS '(' e ')'   { $$ = cos($3); }
    | TAN '(' e ')' {
    if (fabs(cos($3)) < 1e-9) {
        printf("Erreur sémantique : tan(%f) non définie\n", $3);
        syntax_ok = 0;
        $$ = 0;
    } else {
        $$ = tan($3);
    }
    }

    | SIN '(' ')' {
    printf("Erreur sémantique : sin() nécessite un argument\n");
    syntax_ok = 0;
    $$ = 0;
    }

    | COS '(' ')' {
        printf("Erreur sémantique : cos() nécessite un argument\n");
        syntax_ok = 0;
        $$ = 0;
    }

    | LN '(' ')' {
        printf("Erreur sémantique : ln() nécessite un argument > 0\n");
        syntax_ok = 0;
        $$ = 0;
    }
    
    | SQRT '(' ')' {
        printf("Erreur sémantique : sqrt() nécessite un argument\n");
        syntax_ok = 0;
        $$ = 0;
    }


    | EXP '(' e ')'   { $$ = exp($3); }
    | LN '(' e ')' {
    if ($3 <= 0) {
        printf("Erreur sémantique : ln(%f) n'est pas défini\n", $3);
        syntax_ok = 0;
        $$ = 0;
    } else {
        $$ = log($3);
    }
    }

    | SQRT '(' e ')' {
    if ($3 < 0) {
        printf("Erreur sémantique : sqrt(%f) n'est pas défini\n", $3);
        syntax_ok = 0;
        $$ = 0;
    } else {
        $$ = sqrt($3);
    }
    }

 ;

 liste:
      e {
            $$.somme = $1;
            $$.produit = $1;
            $$.nb = 1;
        }
    | liste ',' e {
            $$.somme = $1.somme + $3;
            $$.produit = $1.produit * $3;
            $$.nb = $1.nb + 1;
        }
;

%%

int yyerror(const char *s)
{
    fprintf(stderr,
            "erreur de syntaxe. Les formats acceptables sont :\n"
            " - a (entier ou nombre decimal)\n"
            " - a operateur b\n"
            " - (expression arithmetique) operateur (expression arithmetique)\n");
    syntax_ok = 0;
    return 0;
}

int main(void)
{
     printf("Entrez une expression arithmétique :\n");
    yyparse();

    if (!syntax_ok) {
        printf("Analyse échouée à cause d'erreurs sémantiques.\n");
    }

    return 0;
}
