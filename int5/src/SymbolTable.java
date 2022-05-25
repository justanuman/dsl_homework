
import java.util.HashMap;
import java.util.Map;

public class SymbolTable {
    SymbolTable enclosing;
    private Map<String, Object> values = new HashMap<>();

    SymbolTable() {
        enclosing = null;
    }

    SymbolTable(SymbolTable enclosing) {
        this.enclosing = enclosing;
    }

    Object get(Token name) {
        if (values.containsKey(name.lexem)) {
            return values.get(name.lexem);
        }
        if (enclosing != null){ return enclosing.get(name);}
        System.out.println("wrong var");
        return null;
    }


    void assign(Token name, Object value) {
        if (values.containsKey(name.lexem)) {
            values.put(name.lexem, value);
            return;
        }
        if (enclosing != null) {
            enclosing.assign(name, value);
            return;
        }
        System.out.println("wrong var" + name.lexem);
    }

    void define(String name, Object value) {
        values.put(name, value);
    }

    SymbolTable ancestor(int distance) {
        SymbolTable environment = this;
        for (int i = 0; i < distance; i++) {
            environment = environment.enclosing;
        }
        return environment;
    }

    Object getAt(int distance, String name) {
        return ancestor(distance).values.get(name);
    }

    void assignAt(int distance, Token name, Object value) {
        ancestor(distance).values.put(name.lexem, value);
    }

    @Override
    public String toString() {
        String result = values.toString();
        if (enclosing != null) {
            result += " > " + enclosing.toString();
        }
        return result;
    }

}/*
class FunkReturn extends RuntimeException {
    Object value;
    FunkReturn(Object value) {
        super(null, null, false, false);
        this.value = value;
    }
}*/