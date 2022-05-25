import java.util.List;
interface IntCallable {
    int arity();//rank
    Object call(Interpreter interpreter, List<Object> arguments);
}