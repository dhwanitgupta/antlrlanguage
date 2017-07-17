import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class ThrowingErrorListener extends BaseErrorListener {

    private StringBuilder errorStringBuilder = new StringBuilder();
    private Boolean errorOccured = Boolean.FALSE;

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e)
            throws ParseCancellationException {
        errorOccured = Boolean.TRUE;
        errorStringBuilder.append("Line:Col " + line + ":" + charPositionInLine + " -> " + msg + '\n');
    }

    public String getErrors() {
        return this.errorStringBuilder.toString();
    }

    public Boolean isErrorOccured() {
        return this.errorOccured;
    }
}