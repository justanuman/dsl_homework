import java.util.List;

abstract class Stmt {
    abstract <R> R accept(Visitor2<R> visitor);
}

class Block extends Stmt {
    Block(List<Stmt> statements) {
        this.statements = statements;
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    final List<Stmt> statements;

    @Override
    public String toString() {
        return "Block{" +
                "statements=" + statements +
                '}';
    }
}

class Expression extends Stmt {
    Expression(Expr expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "expression=" + expression +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Expr expression;
}

class Function extends Stmt {
    Function(Token name, List<Token> params, List<Stmt> body) {
        this.name = name;
        this.params = params;
        this.body = body;
    }

    @Override
    public String toString() {
        return "Function{" +
                "name=" + name +
                ", params=" + params +
                ", body=" + body +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Token name;
    List<Token> params;
    List<Stmt> body;
}

class If extends Stmt {
    If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public String toString() {
        return "If{" +
                "condition=" + condition +
                ", thenBranch=" + thenBranch +
                ", elseBranch=" + elseBranch +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Expr condition;
    Stmt thenBranch;
    Stmt elseBranch;
}

class Print extends Stmt {
    Print(Expr expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "Print{" +
                "expression=" + expression +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Expr expression;
}

class Return extends Stmt {
    Return(Token keyword, Expr value) {
        this.keyword = keyword;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Return{" +
                "keyword=" + keyword +
                ", value=" + value +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Token keyword;
    Expr value;
}

class Var extends Stmt {
    Var(Token name, Expr initializer) {
        this.name = name;
        this.initializer = initializer;
    }

    @Override
    public String toString() {
        return "Var{" +
                "name=" + name +
                ", initializer=" + initializer +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Token name;
    Expr initializer;
}

class While extends Stmt {
    While(Expr condition, Stmt body) {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString() {
        return "While{" +
                "condition=" + condition +
                ", body=" + body +
                '}';
    }

    @Override
    <R> R accept(Visitor2<R> visitor) {
        return visitor.visit(this);
    }

    Expr condition;
    Stmt body;
}