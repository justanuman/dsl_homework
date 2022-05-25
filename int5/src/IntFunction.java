
import java.util.List;

class IntFunction implements IntCallable {
    private  Function declaration;
    private  SymbolTable closure;
    IntFunction(Function declaration, SymbolTable closure) {
        this.closure = closure;
        this.declaration = declaration;
    }

    @Override
    public String toString() {
        return "FUNK" + declaration.name.lexem;
    }
    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        SymbolTable environment = new SymbolTable(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexem,
                    arguments.get(i));
        }
        interpreter.executeBlock(declaration.body, environment);
        return null;
    }
}
