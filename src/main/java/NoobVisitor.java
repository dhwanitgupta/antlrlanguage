/**
 * Created by dhwanit on 09/07/17.
 */
public class NoobVisitor extends NoobParserBaseVisitor<Object> {

    @Override
    public Boolean visitFunction(NoobParser.FunctionContext ctx) {

        if (ctx.getText().equals("T")) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean visitBinaryExpression(NoobParser.BinaryExpressionContext ctx){
        System.out.println("Left = " + ctx.left.getText() + " Right = " + ctx.right.getText());
        if (ctx.op.AND() != null) {
            return (Boolean)visit(ctx.left) && (Boolean)visit(ctx.right);
        }
        if (ctx.op.OR() != null) {
            return (Boolean)visit(ctx.left) || (Boolean)visit(ctx.right);
        }

        throw new RuntimeException("Not implemented");
    }

    @Override
    public Boolean visitParenExpression(NoobParser.ParenExpressionContext ctx) {
        return (Boolean)super.visit(ctx.expression());
    }
}
