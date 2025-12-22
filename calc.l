%{
#include <stdlib.h>
#include <math.h>
#include "calc.h"
#include "y.tab.h"
%}

%%

"somme"       return SOMME;
"produit"      return PRODUIT;
"moyenne"      return MOYENNE;
"variance"     return VARIANCE;
"ecart_type"   return ECART_TYPE;
"min"          return MIN;
"max"          return MAX;

"sin"          return SIN;
"cos"          return COS;
"tan"          return TAN;
"exp"          return EXP;
"ln"           return LN;
"log"          return LOG;
"sqrt"         return SQRT;
"puissance"    return POW;


"**"           return POW_OP;

"pi" {
    yylval.node.val = M_PI;
    snprintf(yylval.node.repr, sizeof(yylval.node.repr), "pi");
    return N;
}

"e" {
    yylval.node.val = M_E;
    snprintf(yylval.node.repr, sizeof(yylval.node.repr), "e");
    return N;
}

[0-9]+(\.[0-9]+)? {
    yylval.node.val = atof(yytext);
    snprintf(yylval.node.repr, sizeof(yylval.node.repr), "%s", yytext);
    return N;
}



[ \t]+         ;
\n             return '\n';

"+"|"-"|"*"|"/"|"("|")" return yytext[0];
","                    return ',';

. {
    printf("Caractère invalide : %s\n", yytext);
    return 0;
}

%%

int yywrap(void) { return 1; }
