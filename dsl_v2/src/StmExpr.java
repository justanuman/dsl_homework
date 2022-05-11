import java.util.List;

abstract class StmExpr {
    abstract <R> R accept(IStmVisit<R> visitor);
}

class Function extends StmExpr {
    TokenStore name;
    List<TokenStore> params;
    List<StmExpr> body;

    Function(TokenStore name, List<TokenStore> params, List<StmExpr> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }

}

class Block extends StmExpr {
    List<StmExpr> statements;

    Block(List<StmExpr> statements) {
        this.statements = statements;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class Expression extends StmExpr {
    ExprStm Expression;

    Expression(ExprStm Expression) {
        this.Expression = Expression;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class If extends StmExpr {
    ExprStm condition;
    StmExpr thenBranch;
    StmExpr elseBranch;

    If(ExprStm condition, StmExpr thenBranch, StmExpr elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class Print extends StmExpr {
    ExprStm Expression;

    Print(ExprStm Expression) {
        this.Expression = Expression;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class Return extends StmExpr {
    TokenStore keyword;
    ExprStm value;

    Return(TokenStore keyword, ExprStm value) {
        this.keyword = keyword;
        this.value = value;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class Var extends StmExpr {
    TokenStore name;
    ExprStm initializer;

    Var(TokenStore name, ExprStm initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class While extends StmExpr {
    ExprStm condition;
    StmExpr body;

    While(ExprStm condition, StmExpr body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}

class ListN extends StmExpr {
    TokenStore name;
    ExprStm inside;

    ListN(ExprStm inside, TokenStore name) {
        this.name = name;
        this.inside = inside;
    }

    @Override
    <R> R accept(IStmVisit<R> visitor) {
        return visitor.visit(this);
    }
}