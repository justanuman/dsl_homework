import java.util.List;

public class Intepreter implements Ivisit<Object> , IStmVisit<Void> {
    private Object evaluate(ExprStm expr) {
        return expr.accept(this);
    }
    private void execute(StmExpr stmt) {
        stmt.accept(this);
    }
    @Override
    public Object visit(Assign expr) {
        return null;
    }

    @Override
    public Object visit(BinaryOP expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);
      // System.out.println(left+" "+right);
        if(expr.operator.val.equals("-")){
                return (int)left - (int)right;}
        if(expr.operator.val.equals("/")){
                return (int)left / (int)right;}
        if(expr.operator.val.equals("*")){
                return (int)left * (int)right;
        }if(expr.operator.val.equals("+")){
           // System.out.println(Integer.valueOf(left.toString()));
            if( left instanceof Integer && right instanceof Integer){
                return (int) left + (int) right;
            }
            if(left instanceof String && right instanceof String){
                return (String)left + (String)right;
           }
        }
        /* добавить логику для сравнений*/
        if(expr.operator.val.equals(">")){
            return (int)left > (int)right;}
        if(expr.operator.val.equals("<")){
            return (int)left < (int)right;}
        if(expr.operator.val.equals("<=")){
            return (int)left <= (int)right;
        }if(expr.operator.val.equals(">=")){
            return (int)left >= (int)right;
        }if(expr.operator.val.equals("==")||expr.operator.val.equals("!=")){
                if( expr.operator.val.equals("==")){
                return (int)left == (int)right;
            }
            if(expr.operator.val.equals("!=")){
                return (int)left != (int)right;
            }
        }

        System.out.println("binary error");
        return null;
    }

    @Override
    public Object visit(Call expr) {
        return null;
    }

    @Override
    public Object visit(Get expr) {
        return null;
    }

    @Override
    public Object visit(Grouping expr) {
        return  evaluate(expr.expression);
    }

    @Override
    public Object visit(Literal expr) {
        return expr.value;
    }

    @Override
    public Object visit(Logical expr) {
        return null;
    }

    @Override
    public Object visit(Set expr) {
        return null;
    }

    @Override
    public Object visit(Super expr) {
        return null;
    }

    @Override
    public Object visit(This expr) {
        return null;
    }

    @Override
    public Object visit(UnaryOP expr) {
        Object right = evaluate(expr.right);

        if(expr.operator.val.equals("-")){
            return -1* ((Integer) right);
        }
        if(expr.operator.val.equals("!")){
            if(right == null ||right.equals(false)){
                return true;
            }else {
                return false;
            }
        }

        System.out.println("unaryOP error");
        return null;
    }

    @Override
    public Object visit(Variable expr) {
        return null;
    }

    @Override
    public Object visit(ListNode expr) {
        return null;
    }

    public void interpret(List<StmExpr> statements) {
        System.out.println("pain");
        for(int i=0; i<statements.size();i++){
            System.out.println("pain");
            execute(statements.get(i));

        }
        return;
    }

    public static void main(String[] args) {
        Intepreter intepreter = new Intepreter();
        ExprStm expression;
       // Lexer lex = Lexer.LexInit("\"a\"+ \"b\"");
        Lexer lex = Lexer.LexInit(" PRINT 2 + 1;");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser parser = new Parser(toks);
        //expression = parser.startSynAnal();
        List<StmExpr> statements = parser.parse();
        System.out.println(statements);
        intepreter.interpret(statements);
    }

    @Override
    public Void visit(Block StmExpr) {
        return null;
    }

    @Override
    public Void visit(Expression StmExpr) {
        return null;
    }

    @Override
    public Void visit(Function StmExpr) {
        return null;
    }

    @Override
    public Void visit(If StmExpr) {
        return null;
    }

    @Override
    public Void visit(Print StmExpr) {
        Object value = evaluate(StmExpr.Expression);
        System.out.println(value.toString());
        return null;
    }

    @Override
    public Void visit(Return StmExpr) {
        return null;
    }

    @Override
    public Void visit(Var StmExpr) {
        return null;
    }

    @Override
    public Void visit(While StmExpr) {
        return null;
    }

    @Override
    public Void visit(ListN StmExpr) {
        return null;
    }
}
