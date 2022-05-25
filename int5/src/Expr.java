import java.util.List;

abstract class Expr {
    abstract <R> R accept(Visitor<R> visitor);
}
class Assign extends Expr {
    public  Token name;
    public Expr value;
    Assign(Token name, Expr value) {
        this.name = name;
        this.value = value;
    }
    @Override
    <R> R accept(Visitor<R> visitor) {
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
class Binary extends Expr {
    public Expr left;
    public Token operator;
    public Expr right;
    Binary(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Binary{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                '}';
    }
}
class Call extends Expr {
    public Expr callee;
    public Token paren;
    public List<Expr> arguments;
    Call(Expr callee, Token paren, List<Expr> arguments) {
        this.callee = callee;
        this.paren = paren;
        this.arguments = arguments;
    }
    @Override
    <R> R accept(Visitor<R> visitor) {
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

class Get extends Expr {
    public Expr object;
    public Token name;
    Get(Expr object, Token name) {
        this.object = object;
        this.name = name;
    }
    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Get{" +
                "object=" + object +
                ", name=" + name +
                '}';
    }
}
class Set extends Expr {
    Set(Expr object, Token name, Expr value) {
        this.object = object;
        this.name = name;
        this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

     Expr object;
     Token name;
     Expr value;
}
class Grouping extends Expr {
    Grouping(Expr expression) {
        this.expression = expression;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    final Expr expression;
}
class Literal extends Expr {
    public Object value;
    Literal(Object value) {
        this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Literal{" +
                "value=" + value +
                '}';
    }
}
class Logical extends Expr {
    public Expr left;
    public Token operator;
    public Expr right;
    Logical(Expr left, Token operator, Expr right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Logical{" +
                "left=" + left +
                ", operator=" + operator +
                ", right=" + right +
                '}';
    }
}
/*
class Set extends Expr {
    Set(Expr object, Token name, Expr value) {
        this.object = object;
        this.name = name;
        this.value = value;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

    Expr object;
    Token name;
    Expr value;

    @Override
    public String toString() {
        return "Set{" +
                "object=" + object +
                ", name=" + name +
                ", value=" + value +
                '}';
    }
}*/

class This extends Expr {
    This(Token keyword) {
        this.keyword = keyword;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

     Token keyword;

    @Override
    public String toString() {
        return "This{" +
                "keyword=" + keyword +
                '}';
    }
}
class Unary extends Expr {
    Unary(Token operator, Expr right) {
        this.operator = operator;
        this.right = right;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

     Token operator;
     Expr right;

    @Override
    public String toString() {
        return "Unary{" +
                "operator=" + operator +
                ", right=" + right +
                '}';
    }
}
class Variable extends Expr {
    Variable(Token name) {
        this.name = name;
    }

    @Override
    <R> R accept(Visitor<R> visitor) {
        return visitor.visit(this);
    }

     Token name;

    @Override
    public String toString() {
        return "Variable{" +
                "name=" + name +
                '}';
    }
}

