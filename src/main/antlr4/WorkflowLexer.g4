lexer grammar WorkflowLexer;

fragment LETTERS    : [a-zA-Z] ;
fragment DIGITS     : [0-9];

fragment A          : ('A'|'a') ;
fragment N          : ('N'|'n') ;
fragment D          : ('D'|'d') ;
fragment O          : ('O'|'o') ;
fragment R          : ('R'|'r') ;
fragment U          : ('U'|'u');
fragment I          : ('I'|'i');
fragment F          : ('F'|'f');
fragment H          : ('H'|'h');
fragment E          : ('E'|'e');
fragment T          : ('T'|'t');
fragment L          : ('L'|'l');
fragment S          : ('S'|'s');

AND                 : A N D;
OR                  : O R;
RUN                 : R U N;
IF                  : I F;
THEN                : T H E N;
ELSE                : E L S E;
ENDIF               : E N D I F;
FUNCTION_NAME       : LETTERS+;
WS                  : [ \t\r\n] -> skip ;
LEFT_PAREN          : '(';
RIGHT_PAREN         : ')';
