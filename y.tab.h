
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     N = 258,
     SOMME = 259,
     PRODUIT = 260,
     MOYENNE = 261,
     VARIANCE = 262,
     ECART_TYPE = 263,
     SIN = 264,
     COS = 265,
     TAN = 266,
     EXP = 267,
     LN = 268,
     SQRT = 269,
     POW = 270,
     POW_OP = 271,
     MIN = 272,
     MAX = 273
   };
#endif
/* Tokens.  */
#define N 258
#define SOMME 259
#define PRODUIT 260
#define MOYENNE 261
#define VARIANCE 262
#define ECART_TYPE 263
#define SIN 264
#define COS 265
#define TAN 266
#define EXP 267
#define LN 268
#define SQRT 269
#define POW 270
#define POW_OP 271
#define MIN 272
#define MAX 273




#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 1676 of yacc.c  */
#line 15 "calc.y"

    Node node;
    struct {
        double somme;
        double produit;
        int nb;
        double somme_carre;
        double min;
        double max;
    } list;



/* Line 1676 of yacc.c  */
#line 102 "y.tab.h"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


