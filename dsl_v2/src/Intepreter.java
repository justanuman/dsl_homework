import java.util.List;

public class Intepreter implements Ivisit<Object>{
    private Object evaluate(ExprStm expr) {
        return expr.accept(this);
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
            //if( left instanceof Integer && right instanceof Integer){
                return (int) left + (int) right;
            /*}
            /*if(left instanceof String && right instanceof String){
                return (String)left + (String)right;
           }*/
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
    void interpret(ExprStm expression) {
        Object value = evaluate(expression);
        System.out.println(value.toString());
    }

    public static void main(String[] args) {
        Intepreter intepreter = new Intepreter();
        ExprStm expression;
        Lexer lex = Lexer.LexInit(" 1+2*(1+7) ");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser parser = new Parser(toks);
        expression = parser.startSynAnal();
        System.out.println(expression);
        intepreter.interpret(expression);
    }
}
