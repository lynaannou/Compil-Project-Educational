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

%type <val> e t f
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

t: t '*' f {$$ = $1*$3;}
 | t '/' f { if ($3 == 0) {
             printf("Erreur, division par 0");
             syntax_ok = 0;
             $$ = 0;
             }else{
                $$ = $1 / $3;
             }                  
             }
 | f {$$ = $1;}
 ;

f: '(' e ')' {$$ = $2;}
 | '-' f    {$$ = -$2;}
 | N        {$$ = $1;}
 /* fonctions à N arguments */
    | SOMME '(' liste ')'    { $$ = $3.somme; }
    | PRODUIT '(' liste ')'  { $$ = $3.produit; }
    | MOYENNE '(' liste ')'  { $$ = $3.somme / $3.nb; }

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
