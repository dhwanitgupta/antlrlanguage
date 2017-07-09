import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static java.lang.System.lineSeparator;

public class MarkupVisitor extends MarkupParserBaseVisitor<String>
{
    private PrintStream stream;
    public MarkupVisitor(PrintStream stream)
    {
        this.stream = stream;
    }

    @Override
    public String visitFile(MarkupParser.FileContext context)
    {
        visitChildren(context);

        stream.println();

        return null;
    }

    @Override
    public String visitTag(MarkupParser.TagContext context)
    {
        String text = "";
        String startDelimiter = "", endDelimiter = "";

        String id = context.ID(0).getText();


        if (id.equals("b")) {
            startDelimiter = endDelimiter = "**";
        } else if(id.equals("u")) {
            startDelimiter = endDelimiter = "*";
        } else if (id.equals("quote")) {
                String attribute = context.attribute().STRING().getText();
                attribute = attribute.substring(1, attribute.length() - 1);
                startDelimiter = lineSeparator() + "> ";
                endDelimiter = lineSeparator() + "> " + lineSeparator() + "> – "
                        + attribute + lineSeparator();
        }


        text += startDelimiter;

        for (MarkupParser.ElementContext node: context.element())
        {
            if(node.tag() != null)
                text += visitTag(node.tag());
            if(node.content() != null)
                text += visitContent(node.content());
        }

        text += endDelimiter;

        return text;
    }

    @Override
    public String visitContent(MarkupParser.ContentContext context)
    {
        return context.getText();
    }

    @Override
    public String visitElement(MarkupParser.ElementContext context)
    {
        if(context.parent instanceof MarkupParser.FileContext)
        {
            if(context.content() != null)
                stream.print(visitContent(context.content()));

            if(context.tag() != null)
                stream.print(visitTag(context.tag()));
        }

        return null;
    }
}