%{
#include "y.tab.h"
#include <stdlib.h>
%}

%%

"somme"      return SOMME;
"produit"    return PRODUIT;
"moyenne"    return MOYENNE;
"sin"       return SIN;
"cos"       return COS;
"tan"       return TAN;
"exp"       return EXP;
"ln"        return LN;
"sqrt"      return SQRT;
"puissance"    return POW;
"**"           return POW_OP;

[0-9]+(\.[0-9]+)? {
    yylval.val = atof(yytext);
    return N;
}

[ \t]+      { /* ignorer espaces */ }
\n          { return '\n'; }

"+"|"-"|"*"|"/"|"("|")" { return yytext[0]; }
","                    { return ','; }

. {
    printf("Caractère invalide : %s\n", yytext);
    return 0;
}

%%

int yywrap(void)
{
    return 1;
}

