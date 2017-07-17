import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Init
{
    public static void main( String[] args ) throws Exception {

        FileReader fr = new FileReader(new Init().getFileName());
        BufferedReader br = new BufferedReader(fr);

        ANTLRInputStream inputStream = new ANTLRInputStream(br);
        WorkflowLexer noobLexer = new WorkflowLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(noobLexer);
        WorkflowParser workflowParser = new WorkflowParser(commonTokenStream);
        workflowParser.removeErrorListeners();
        ThrowingErrorListener throwingErrorListener = new ThrowingErrorListener();
        workflowParser.addErrorListener(throwingErrorListener);
        WorkflowParser.ProgramContext fileContext = workflowParser.program();
        if (throwingErrorListener.isErrorOccured()) {
            throw new Exception("Parse Syntax Error: \n" + throwingErrorListener.getErrors());
        }
        WorkflowVisitor visitor = new WorkflowVisitor();
        System.out.println(visitor.visit(fileContext));
    }

    public String getFileName() {
        ClassLoader classLoader = getClass().getClassLoader();

        return classLoader.getResource("input").getFile();
    }
}