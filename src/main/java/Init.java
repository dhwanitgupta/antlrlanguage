import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Init
{
    public static void main( String[] args )
    {
        ANTLRInputStream inputStream = new ANTLRInputStream(
                "F and (T or T)");
        NoobLexer noobLexer = new NoobLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(noobLexer);
        NoobParser noobParser = new NoobParser(commonTokenStream);

        NoobParser.NoobContext fileContext = noobParser.noob();

        NoobVisitor visitor = new NoobVisitor();
        System.out.println(visitor.visit(fileContext));
    }
}