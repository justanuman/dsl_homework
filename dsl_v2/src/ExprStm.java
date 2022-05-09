import java.util.List;

abstract class ExprStm {
    abstract <R> R accept(Ivisit<R> visitor);

}

class BinaryOP extends ExprStm {
    ExprStm left;
    TokenStore operator;
    ExprStm right;

    BinaryOP(ExprStm left, TokenStore operator, ExprStm right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "{"  +left + operator.val + right + '}';
    }
}

class Assign extends ExprStm {
    TokenStore name;
    ExprStm value;

    Assign(TokenStore name, ExprStm value) {
        this.name = name;
        this.value = value;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Assign{" +
                "name=" + name +
                ", value=" + value +
                '}';
    }
}

class Call extends ExprStm {
    ExprStm callee;
    TokenStore paren;
    List<ExprStm> arguments;

    Call(ExprStm callee, TokenStore paren, List<ExprStm> arguments) {
        this.callee = callee;
        this.paren = paren;
        this.arguments = arguments;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Call{" +
                "callee=" + callee +
                ", paren=" + paren +
                ", arguments=" + arguments +
                '}';
    }
}

class Get extends ExprStm {
    ExprStm object;
    TokenStore name;

    Get(ExprStm object, TokenStore name) {
        this.object = object;
        this.name = name;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Get{" +
                object +
                name +
                '}';
    }
}

class Grouping extends ExprStm {
    ExprStm expression;

    Grouping(ExprStm expression) {
        this.expression = expression;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "{" +
                expression +
                '}';
    }
}

class Literal extends ExprStm {
    Object value;
    Literal(Object value) {
        this.value = value;
    }
    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return value.toString();
    }
}


class Logical extends ExprStm {
    ExprStm left;
    TokenStore operator;
    ExprStm right;

    Logical(ExprStm left, TokenStore operator, ExprStm right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Logical{" +
                left +
                operator +
                right +
                '}';
    }
}

class Set extends ExprStm {
    ExprStm object;
    TokenStore name;
    ExprStm value;

    Set(ExprStm object, TokenStore name, ExprStm value) {
        this.object = object;
        this.name = name;
        this.value = value;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Set{" +
                "object=" + object +
                ", name=" + name +
                ", value=" + value +
                '}';
    }
}

class Super extends ExprStm {
    TokenStore keyword;
    TokenStore method;

    Super(TokenStore keyword, TokenStore method) {
        this.keyword = keyword;
        this.method = method;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Super{" +
                "keyword=" + keyword +
                ", method=" + method +
                '}';
    }
}

class This extends ExprStm {
    TokenStore keyword;

    This(TokenStore keyword) {
        this.keyword = keyword;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "{" +
                "keyword=" + keyword +
                '}';
    }
}

class UnaryOP extends ExprStm {
    TokenStore operator;
    ExprStm right;

    UnaryOP(TokenStore operator, ExprStm right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "{" +
                operator +
                right +
                '}';
    }
}

class Variable extends ExprStm {
    TokenStore name;

    Variable(TokenStore name) {
        this.name = name;
    }

    @Override
    <R> R accept(Ivisit<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name=" + name +
                '}';
    }
}