public interface Ivisit<R> {
        R visit(Assign expr);
        R visit(BinaryOP expr);
        R visit(Call expr);
        R visit(Get expr);
        R visit(Grouping expr);
        R visit(Literal expr);
        R visit(Logical expr);
        R visit(Set expr);
        R visit(Super expr);
        R visit(This expr);
        R visit(UnaryOP expr);
        R visit(Variable expr);
    }
