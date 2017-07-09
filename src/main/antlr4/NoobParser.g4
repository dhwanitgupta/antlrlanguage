parser grammar NoobParser;

options { tokenVocab=NoobLexer; }

noob         : expression*;

expression   :
                LEFT_PAREN expression RIGHT_PAREN           #parenExpression
              | left=expression op=binary right=expression #binaryExpression
              | function  #functionExpression
              ;



binary       : AND | OR;

function : FUNCTION_NAME;


