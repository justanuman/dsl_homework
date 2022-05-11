public interface IStmVisit<R> {
    R visit(Block StmExpr);
   // R visit(Class StmExpr);
    R visit(Expression StmExpr);
    R visit(Function StmExpr);
    R visit(If StmExpr);
    R visit(Print StmExpr);
    R visit(Return StmExpr);
    R visit(Var StmExpr);
    R visit(While StmExpr);
    R visit(ListN StmExpr);
}
