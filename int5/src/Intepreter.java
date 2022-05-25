import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Interpreter implements Visitor<Object>, Visitor2<Void> {
    SymbolTable globals = new SymbolTable();
    private SymbolTable environment = globals;
    private final Map<Expr, Integer> locals = new HashMap<>();

    void interpret(List<Stmt> statements) {
        for (Stmt statement : statements) {
            execute(statement);
        }
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }

    private void execute(Stmt stmt) {
        stmt.accept(this);
    }

    void resolve(Expr expr, int depth) {
        locals.put(expr, depth);
    }

    void executeBlock(List<Stmt> statements,
                      SymbolTable environment) {
        SymbolTable previous = this.environment;
        try {
            this.environment = environment;

            for (Stmt statement : statements) {
                execute(statement);
            }
        } finally {
            this.environment = previous;
        }
    }

    @Override
    public Void visit(Block stmt) {
        executeBlock(stmt.statements, new SymbolTable(environment));
        return null;
    }


    @Override
    public Void visit(Expression stmt) {
        evaluate(stmt.expression);
        return null;
    }

    @Override
    public Void visit(Function stmt) {
        IntFunction function = new IntFunction(stmt, environment);
        environment.define(stmt.name.lexem, function);
        return null;
    }

    @Override
    public Void visit(If stmt) {
        if (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.thenBranch);
        } else if (stmt.elseBranch != null) {
            execute(stmt.elseBranch);
        }
        return null;
    }

    @Override
    public Void visit(Print stmt) {
        Object value = evaluate(stmt.expression);
        System.out.println(stringify(value));
        return null;
    }

    @Override
    public Void visit(Return stmt) {
        Object value = null;
        /*if (stmt.value != null) {
            value = evaluate(stmt.value);
        }
        throw new FunkReturn(value);*/
        return null;
    }

    @Override
    public Void visit(Var stmt) {
        Object value = null;
        if (stmt.initializer != null) {
            value = evaluate(stmt.initializer);
        }

        environment.define(stmt.name.lexem, value);
        return null;
    }

    @Override
    public Void visit(While stmt) {
        while (isTruthy(evaluate(stmt.condition))) {
            execute(stmt.body);
        }
        return null;
    }

    @Override
    public Object visit(Assign expr) {
        Object value = evaluate(expr.value);
        Integer distance = locals.get(expr);
        if (distance != null) {
            environment.assignAt(distance, expr.name, value);
        } else {
            globals.assign(expr.name, value);
        }
        return value;
    }

    @Override
    public Object visit(Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);
        if (expr.operator.type.equals("EQ")) {
            return isEqual(left, right);
        } else if (expr.operator.type.equals("NOTEQ")) {
            return !isEqual(left, right);
        } else if (expr.operator.type.equals("MORE")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left > (int) right;
        } else if (expr.operator.type.equals("LESS")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left < (int) right;
        } else if (expr.operator.type.equals("MOREOREQ")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left >= (int) right;
        } else if (expr.operator.type.equals("LESSOREQ")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left <= (int) right;
        } else if (expr.operator.type.equals("MINUS")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left - (int) right;
        } else if (expr.operator.type.equals("PLUS")) {
            if (left instanceof Integer && right instanceof Integer) {
                return (int) left + (int) right;
            }

            if (left instanceof String && right instanceof String) {
                return (String) left + (String) right;
            }
        } else if (expr.operator.type.equals("SLASH")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left / (int) right;
        } else if (expr.operator.type.equals("STAR")) {
            checkNumberOperands(expr.operator, left, right);
            return (int) left * (int) right;
        }
        return null;
    }

    @Override
    public Object visit(Set expr) {
        return null;
    }

    @Override
    public Object visit(Call expr) {
        Object callee = evaluate(expr.callee);
        List<Object> arguments = new ArrayList<>();
        for (Expr argument : expr.arguments) {
            arguments.add(evaluate(argument));
        }
        if (!(callee instanceof IntCallable)) {
            //System.out.println(callee);
            System.out.println("call error");
            return null;
        }
        IntCallable function = (IntCallable) callee;
        if (arguments.size() != function.arity()) {
            System.out.println("arity erorr");
            return null;
        }
        return function.call(this, arguments);
    }

    @Override
    public Object visit(Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visit(Literal expr) {
        return expr.value;
    }

    @Override
    public Object visit(Logical expr) {
        Object left = evaluate(expr.left);

        if (expr.operator.type.equals("OR")) {
            if (isTruthy(left)) return left;
        } else {
            if (!isTruthy(left)) return left;
        }
        return evaluate(expr.right);
    }


    @Override
    public Object visit(This expr) {
        return lookUpVariable(expr.keyword, expr);
    }

    @Override
    public Object visit(Unary expr) {
        Object right = evaluate(expr.right);
        if (expr.operator.type.equals("NOT")) {
            return !isTruthy(right);
        } else if (expr.operator.type.equals("MINUS")) {
            checkNumberOperand(expr.operator, right);
            return -(int) right;
        }
        return null;
    }

    @Override
    public Object visit(Variable expr) {
        return lookUpVariable(expr.name, expr);
    }

    @Override
    public Object visit(Get expr) {
        return null;
    }

    private Object lookUpVariable(Token name, Expr expr) {
        Integer distance = locals.get(expr);
        if (distance != null) {
            return environment.getAt(distance, name.lexem);
        } else {
            return globals.get(name);
        }
    }

    private void checkNumberOperand(Token operator, Object operand) {
        if (operand instanceof Integer) return;
        System.out.println("op error");
    }

    private void checkNumberOperands(Token operator, Object left, Object right) {
        if (left instanceof Integer && right instanceof Integer) return;
        System.out.println("op error");
    }

    private boolean isTruthy(Object object) {
        if (object == null) return false;
        if (object instanceof Boolean) return (boolean) object;
        return true;
    }

    private boolean isEqual(Object a, Object b) {
        if (a == null && b == null) return true;
        if (a == null) return false;
        return a.equals(b);
    }

    private String stringify(Object object) {
        if (object == null) return "NONE";
        return object.toString();
    }

    public static void main(String[] args) {
        Expr expression;
        // Lexer lex = Lexer.LexInit(" 1+2+(3+(5*6)) ");
        //Lexer lex = Lexer.LexInit();
        //фор доделать лл доделать
        Lexer lex = Lexer.LexInit(" VAR a = 5 ;" +
                "PRINT \"a\"+\"b\";" +
                "PRINT a;" +
                "WHILE( a>0){a=a-1; PRINT \"rabotatet\";}" +
                "FUNK a(){PRINT 12;}"
                + "a();" +
                "PRINT TRUE AND FALSE;" +
                "PRINT TRUE OR FALSE;");
        List<Token> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser parser = new Parser(toks);
        List<Stmt> statements = parser.parse();
        System.out.println(statements.toString());
        Interpreter intep = new Interpreter();
        intep.interpret(statements);
    }
}
