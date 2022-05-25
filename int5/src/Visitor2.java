public interface Visitor2<R> {
    R visit(Block stmt);
    R visit(Expression stmt);
    R visit(Function stmt);
    R visit(If stmt);
    R visit(Print stmt);
    R visit(Return stmt);
    R visit(Var stmt);
    R visit(While stmt);
}
