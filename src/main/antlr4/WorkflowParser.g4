parser grammar WorkflowParser;

options { tokenVocab=WorkflowLexer; }

program      : line*;

line         : condition | action;

condition    : IF expression THEN program (ELSE program)? ENDIF;

expression   :
                LEFT_PAREN expression RIGHT_PAREN           #parenExpression
              | left=expression op=binary right=expression #binaryExpression
              | function  #functionExpression
              ;

binary       : AND | OR;

action       : RUN function;

function     : FUNCTION_NAME;


