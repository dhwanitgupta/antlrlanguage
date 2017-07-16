parser grammar WorkflowParser;

options { tokenVocab=WorkflowLexer; }

program      : line*;

line         : condition | action;

condition    : IF ifExpression=expression THEN thenProgram=program
               (ELSEIF elseIfExpression=expression THEN elseIfProgram=program)*
               (ELSE elseProgram=program)? ENDIF;

expression   :
                LEFT_PAREN expression RIGHT_PAREN           #parenExpression
              | left=expression op=binary right=expression #binaryExpression
              | action  #actionExpression
              ;

binary       : AND | OR;

action       : RUN functionName=function LEFT_PAREN argumentList=arguments RIGHT_PAREN;

arguments    : argument (COMMA_SEPARATOR arguments)* | ;

argument     : constantArgument=constant | variableArgument=variable;

constant     : string=STRING;

variable     : variableName=ID;

function     : ID;


