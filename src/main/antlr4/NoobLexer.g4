lexer grammar NoobLexer;

fragment LETTERS    : [a-zA-Z] ;
fragment DIGITS     : [0-9];

fragment A          : ('A'|'a') ;
fragment N          : ('N'|'n') ;
fragment D          : ('D'|'d') ;
fragment O          : ('O'|'o') ;
fragment R          : ('R'|'r') ;

AND                 : A N D;
OR                  : O R;
FUNCTION_NAME       : LETTERS+;
WS                  : [ \t\r\n] -> skip ;
LEFT_PAREN          : '(';
RIGHT_PAREN         : ')';