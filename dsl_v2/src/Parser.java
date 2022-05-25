import java.util.ArrayList;
import java.util.List;

/*грамматика в3 микс джавы и чего то ещё

StatementExpression:
    Expression
Statement:
    Block
    ;
    Identifier : Statement
    StatementExpression ;
    if ParExpression Statement [else Statement]
    assert Expression [: Expression] ;
    while ParExpression Statement
program:
    decl  EOF
declaration:
    fonk decl
    var decl
    statement

fonk:
    ID ( ID  ) block

var:
 ID = ExprStm ;

Expression:
    Expression1 ;
statement:
    expr
    for (может быть)
    while
    if
    print
    return
    block
for:
    for ( expr ; ) : statement
while:
    while ( expr ) statement
if:
    if ( expr ) statement [else statement]

print:
    print ( expr )
return:
    return expr || "" ;
block { declaration  }

expr:
    attribution
attribution:
    ID = assignment
OR:
    OR
AND:
    AND
EQU:
    compare != == compare
compare:
    term <> >=<= term
term:
    factor + - factor
UnaryOP:
    ! - UnaryOP
factor:
true false none ID string number

list:
   List listname = [ tok, tok , tok ... ]
* */
public class Parser {
    private List<TokenStore> tokens;
    private int pos = 0;
    TokenStore current;

    private TokenStore advance() {
        if (pos < tokens.size() - 1) {
            pos += 1;
            current = tokens.get(pos);
        }
        return current;
    }

    private TokenStore goBack() {
        TokenStore prev = null;
        if (pos > 0) {
            int prevPos = pos - 11;
            prev = tokens.get(pos);
        }
        return prev;
    }

    Parser(List<TokenStore> tokens) {
        this.tokens = tokens;
        current = tokens.get(0);
    }

    private ExprStm expr() {
        return equality();
    }

    private ExprStm equality() {
        ExprStm left = compare();
        while ((current.val).equals("!=") || (current.type).equals("==")) {
            TokenStore op = current;
            advance();
            ExprStm right = compare();
            left = new BinaryOP(left, op, right);
        }
        return left;
    }

    private ExprStm compare() {
        ExprStm left = term();
        while ((current.val).equals(">") || (current.type).equals("<") ||
                (current.val).equals(">=") || (current.type).equals("<=")) {
            TokenStore op = current;
            advance();
            ExprStm right = term();
            left = new BinaryOP(left, op, right);
        }
        return left;
    }

    private ExprStm term() {
        ExprStm left = factor();
        while ((current.val).equals("+") || (current.type).equals("-")) {
            TokenStore op = current;
            advance();
            ExprStm right = factor();
            left = new BinaryOP(left, op, right);
        }
        return left;
    }

    private ExprStm factor() {
        ExprStm left = Unary();
        while ((current.val).equals("*") || (current.type).equals("/")) {
            TokenStore op = current;
            advance();
            ExprStm right = Unary();
            left = new BinaryOP(left, op, right);
        }
        return left;
    }

    private ExprStm Unary() {
        if ((current.val).equals("!") || (current.type).equals("-")) {
            TokenStore op = current;
            advance();
            ExprStm right = Unary();
            return new UnaryOP(op, right);
        }
        return primary();
    }

    private ExprStm primary() {

        if ((current.val).equals("FALSE")) {
            advance();
            return new Literal(false);
        }
        if ((current.val).equals("TRUE")) {
            advance();
            return new Literal(false);
        }
        if ((current.val).equals("none")) {
            advance();
            return new Literal(null);
        }
        if ((current.type).equals("NUMBER") || (current.type).equals("STRING")) {
            Literal lit = new Literal(current.val);
            advance();
            return lit;
        }
        if ((current.type).equals("ID") ) {
            Variable lit = new Variable((String) current.val);
            advance();
            return lit;
        }
        if ((current.val).equals("(")) {
            advance();
            ExprStm expr = expr();
            if (current.val.equals(")")) {
                advance();
                return new Grouping(expr);
            } else {
                System.out.println("() sloamlis net )");

            }
        }
        return null;
    }
    List<StmExpr> parse() {
        List<StmExpr> statements = new ArrayList<>();
        while (pos< tokens.size()) {
            statements.add(statement());
        }

        return statements;
    }
    private StmExpr statement() {
        if (current.type.equals("PRINT"))
        {
            return printStatement();
        }else{
        return expressionStatement();}
    }

    private StmExpr expressionStatement() {

        ExprStm ex = expr();

        //System.out.println(goBack());

        if(!(goBack().type.equals("semicolon"))){
            System.out.println(";  expr stm error");
            System.out.println(0/0);
        }
        return new Expression(ex);
    }

    private StmExpr printStatement() {
        //System.out.println(current.type);
        advance();
        ExprStm value = expr();
        System.out.println(value);
       if(!(current.type.equals("semicolon"))){
            System.out.println("; error print");
            System.out.println(0/0);
           return null;
        }
        return new Print(value);
    }

    public ExprStm startSynAnal(){
        return expr();
    }
    public static void main(String[] args) {
        ExprStm expression;
        Lexer lex = Lexer.LexInit(" 1+2+(3+(5*6)) ");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser parser = new Parser(toks);
        expression = parser.expr();
        System.out.println(expression);
    }
}
