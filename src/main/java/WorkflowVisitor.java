import java.util.List;

/**
 * Created by dhwanit on 15/07/17.
 */
public class WorkflowVisitor extends WorkflowParserBaseVisitor<Object> {

    @Override
    public Boolean visitBinaryExpression(WorkflowParser.BinaryExpressionContext ctx){
        //System.out.println("Left = " + ctx.left.getText() + " Right = " + ctx.right.getText());
        if (ctx.op.AND() != null) {
            return (Boolean)visit(ctx.left) && (Boolean)visit(ctx.right);
        }
        if (ctx.op.OR() != null) {
            return (Boolean)visit(ctx.left) || (Boolean)visit(ctx.right);
        }

        throw new RuntimeException("Not implemented");
    }

    @Override
    public Object visitVariableExpression(WorkflowParser.VariableExpressionContext ctx) {
        return Boolean.FALSE;
    }

    @Override
    public Object visitCondition(WorkflowParser.ConditionContext ctx) {
        List<WorkflowParser.ExpressionContext> expressionContexts = ctx.expression();
        List<WorkflowParser.ProgramContext> programContexts = ctx.program();

        for (int iter = 0; iter < expressionContexts.size(); iter++) {
            if ((Boolean)visit(expressionContexts.get(iter))) {
                return visit(programContexts.get(iter));
            }
        }

        if (ctx.elseProgram != null) {
            return visit(ctx.elseProgram);
        }

        return null;
    }

    @Override
    public Boolean visitActionExpression(WorkflowParser.ActionExpressionContext ctx) {
        return (Boolean)super.visit(ctx.action());
    }

    @Override
    public Boolean visitParenExpression(WorkflowParser.ParenExpressionContext ctx) {
        return (Boolean)super.visit(ctx.expression());
    }

    @Override
    public Object visitAction(WorkflowParser.ActionContext ctx) {
        Object result = callFunction(ctx.functionName, ctx.argumentList);
        if (result instanceof Boolean) {
            return result;
        }
        return null;
    }

    private Object callFunction(WorkflowParser.FunctionContext functionName, WorkflowParser.ArgumentsContext argumentList) {
        //System.out.println("I am in the function " + functionName.getText() + " With arguments " + argumentList.getText());
        System.out.println(functionName.getText() + " >> " + argumentList.getText());
        if (argumentList.getText().equals("T")) {
            return Boolean.TRUE;
        } else if (argumentList.getText().equals("F")) {
            return Boolean.FALSE;
        }
        return null;
    }
}
