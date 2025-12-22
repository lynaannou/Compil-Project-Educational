%{
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include "calc.h"

int yylex(void);
int yyerror(const char *s);
int syntax_ok = 1;



%}

%union {
    Node node;
    struct {
        double somme;
        double produit;
        int nb;
        double somme_carre;
        double min;
        double max;
    } list;
}

%token <node> N
%token SOMME PRODUIT MOYENNE VARIANCE ECART_TYPE
%token SIN COS TAN EXP LN SQRT LOG
%token POW POW_OP
%token MIN MAX

%right POW_OP

%type <node> e t p f
%type <list> liste

%%

input:
    e '\n' {
        if (syntax_ok) {
            printf("Expression correcte !\n");
            printf("Result = %f\n", $1.val);
            printf("Parse tree: %s\n", $1.repr);
        }
    }
;

e: e '+' t {
        $$.val = $1.val + $3.val;
        snprintf($$.repr, sizeof($$.repr), "(+ %s %s)", $1.repr, $3.repr);
    }
 | e '-' t {
        $$.val = $1.val - $3.val;
        snprintf($$.repr, sizeof($$.repr), "(- %s %s)", $1.repr, $3.repr);
    }
 | t {
        $$.val = $1.val;
        snprintf($$.repr, sizeof($$.repr), "%s", $1.repr);
    }
;

t: t '*' p {
        $$.val = $1.val * $3.val;
        snprintf($$.repr, sizeof($$.repr), "(* %s %s)", $1.repr, $3.repr);
    }
 | t '/' p {
        if ($3.val == 0) {
            printf("Erreur, division par 0\n");
            syntax_ok = 0; $$.val = 0;
            snprintf($$.repr, sizeof($$.repr), "(/ %s %s)", $1.repr, $3.repr);
        } else {
            $$.val = $1.val / $3.val;
            snprintf($$.repr, sizeof($$.repr), "(/ %s %s)", $1.repr, $3.repr);
        }
    }
 | p {
        $$.val = $1.val;
        snprintf($$.repr, sizeof($$.repr), "%s", $1.repr);
    }
;

p:
      f
    | f POW_OP p {
        $$.val = pow($1.val, $3.val);
        snprintf($$.repr, sizeof($$.repr), "(^ %s %s)", $1.repr, $3.repr);
    }
;

f:
      '(' e ')' { $$.val = $2.val; snprintf($$.repr, sizeof($$.repr), "(%s)", $2.repr); }
    | '-' f { $$.val = -$2.val; snprintf($$.repr, sizeof($$.repr), "(- %s)", $2.repr); }
    | N { $$.val = $1.val; snprintf($$.repr, sizeof($$.repr), "%g", $1.val); }

    /* N-arg functions */
    | SOMME '(' liste ')' { $$.val = $3.somme; snprintf($$.repr, sizeof($$.repr), "(SOMME ...)"); }
    | PRODUIT '(' liste ')' { $$.val = $3.produit; snprintf($$.repr, sizeof($$.repr), "(PRODUIT ...)"); }
    | MOYENNE '(' liste ')' { $$.val = $3.somme / $3.nb; snprintf($$.repr, sizeof($$.repr), "(MOYENNE ...)"); }
    | VARIANCE '(' liste ')' {
        double m = $3.somme / $3.nb;
        $$.val = ($3.somme_carre / $3.nb) - (m * m);
        snprintf($$.repr, sizeof($$.repr), "(VARIANCE ...)");
    }
    | ECART_TYPE '(' liste ')' {
        double m = $3.somme / $3.nb;
        double v = ($3.somme_carre / $3.nb) - (m * m);
        $$.val = sqrt(v);
        snprintf($$.repr, sizeof($$.repr), "(ECART_TYPE ...)");
    }
    | LOG '('e', 'e')' {
        double m = log($3.val);
        double n = log($5.val);
        $$.val = m/n;
        snprintf($$.repr, sizeof($$.repr), "(LOG ...)");
    }
    | MIN '(' liste ')' { $$.val = $3.min; snprintf($$.repr, sizeof($$.repr), "(MIN ...)"); }
    | MAX '(' liste ')' { $$.val = $3.max; snprintf($$.repr, sizeof($$.repr), "(MAX ...)"); }
    | POW '(' e ',' e ')'     { $$.val = pow($3.val, $5.val); snprintf($$.repr, sizeof($$.repr), "(PUISSANCE ...)"); }
    | POW '(' ')' {
    printf("Erreur sémantique : puissance() nécessite deux arguments\n");
    syntax_ok = 0;
    $$.val = 0;
    }
    | POW '(' e ')' {
    printf("Erreur sémantique : puissance(x) invalide, 2 arguments requis\n");
    syntax_ok = 0;
    $$.val = 0;
    }
    | POW '(' e ',' e ',' e ')' {
    printf("Erreur sémantique : puissance(a,b) accepte exactement deux arguments\n");
    syntax_ok = 0;
    $$.val = 0;
    }
    /* 1-arg functions */
    | SIN '(' e ')' { $$.val = sin($3.val); snprintf($$.repr, sizeof($$.repr), "(SIN %s)", $3.repr); }
    | COS '(' e ')' { $$.val = cos($3.val); snprintf($$.repr, sizeof($$.repr), "(COS %s)", $3.repr); }
    | TAN '(' e ')' {
        if (fabs(cos($3.val)) < 1e-9) { printf("Erreur: tan non définie\n"); syntax_ok = 0; $$.val = 0; }
        else $$.val = tan($3.val);
        snprintf($$.repr, sizeof($$.repr), "(TAN %s)", $3.repr);
    }
    | EXP '(' e ')' { $$.val = exp($3.val); snprintf($$.repr, sizeof($$.repr), "(EXP %s)", $3.repr); }
    | LN '(' e ')' {
        if ($3.val <= 0) { printf("Erreur: ln non défini\n"); syntax_ok = 0; $$.val = 0; }
        else $$.val = log($3.val);
        snprintf($$.repr, sizeof($$.repr), "(LN %s)", $3.repr);
    }
    | SQRT '(' e ')' {
        if ($3.val < 0) { printf("Erreur: sqrt non défini\n"); syntax_ok = 0; $$.val = 0; }
        else $$.val = sqrt($3.val);
        snprintf($$.repr, sizeof($$.repr), "(SQRT %s)", $3.repr);
    }
    | SIN '(' ')' {
    printf("Erreur sémantique : sin() nécessite un argument\n");
    syntax_ok = 0;
    $$.val = 0;
    }

    | COS '(' ')' {
        printf("Erreur sémantique : cos() nécessite un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }

    | LN '(' ')' {
        printf("Erreur sémantique : ln() nécessite un argument > 0\n");
        syntax_ok = 0;
        $$.val = 0;
    }

    | SQRT '(' ')' {
        printf("Erreur sémantique : sqrt() nécessite un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
| SOMME '(' ')' {
    printf("Erreur sémantique : somme() nécessite au moins un argument\n");
    syntax_ok = 0;
    $$.val = 0;
    }
    | PRODUIT '(' ')' {
        printf("Erreur sémantique : produit() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | MOYENNE '(' ')' {
        printf("Erreur sémantique : moyenne() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | MIN '(' ')' {
        printf("Erreur sémantique : min() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | MAX '(' ')' {
        printf("Erreur sémantique : max() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | VARIANCE '(' ')' {
        printf("Erreur sémantique : variance() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | ECART_TYPE '(' ')' {
        printf("Erreur sémantique : ecart_type() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | LOG '(' ')' {
        printf("Erreur sémantique : log() nécessite au moins un argument\n");
        syntax_ok = 0;
        $$.val = 0;
    }
    | LOG '('e')' {
        if ($3.val <= 0) { printf("Erreur: log non défini\n"); syntax_ok = 0; $$.val = 0; }
        else $$.val = log($3.val);
        snprintf($$.repr, sizeof($$.repr), "(LN %s)", $3.repr);
    }
;

liste:
      e {
        $$.somme = $1.val;
        $$.somme_carre = $1.val * $1.val;
        $$.produit = $1.val;
        $$.min = $1.val;
        $$.max = $1.val;
        $$.nb = 1;
    }
    | liste ',' e {
        $$.somme = $1.somme + $3.val;
        $$.somme_carre = $1.somme_carre + ($3.val * $3.val);
        $$.produit = $1.produit * $3.val;
        $$.nb = $1.nb + 1;
        $$.min = fmin($1.min, $3.val);
        $$.max = fmax($1.max, $3.val);
    }
;

%%

int yyerror(const char *s) {
    fprintf(stderr, "Erreur de syntaxe.\n");
    syntax_ok = 0;
    return 0;
}

int main(void) {
    printf("Entrez une expression arithmétique :\n");
    yyparse();
    if (!syntax_ok) printf("Analyse échouée.\n");
    return 0;
}
