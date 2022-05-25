public interface Visitor<R> {
    R visit(Assign expr);
    R visit(Binary expr);
    R visit(Set expr);
    R visit(Call expr);
    R visit(Grouping expr);
    R visit(Literal expr);
    R visit(Logical expr);
    R visit(This expr);
    R visit(Unary expr);
    R visit(Variable expr);
    R visit(Get expr);
}
