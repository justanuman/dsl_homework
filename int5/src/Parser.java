import java.util.ArrayList;
import java.util.Arrays;
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
    for
    while
    if
    print
    block
for:
    for ( expr ; ) : statement
while:
    while ( expr ) statement
if:
    if ( expr ) statement [else statement]

print:
    print ( expr )
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
class Parser {
    private List<Token> tokens;
    private int current = 0;

    Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!isEnd()) {
            statements.add(declaration());
        }
        return statements;
    }

    private Expr expression() {
        return assignment();
    }

    private Stmt declaration() {
        if (match("FUNK")) return function();
        if (match("VAR")) return varDeclaration();
        return statement();
    }

    private Stmt statement() {
        if (match("FOR")) return forStatement();
        if (match("IF")) return ifStatement();
        if (match("PRINT")) return printStatement();
        if (match("WHILE")) return whileStatement();
        if (match("LEFT_BRACE")) return new Block(block());
        return expressionStatement();
    }

    private Stmt forStatement() {
        eat("LEFT_PAREN");
        Stmt initializer;
        if (match("SEMICOLON")) {
            initializer = null;
        } else if (match("VAR")) {
            initializer = varDeclaration();
        } else {
            initializer = expressionStatement();
        }
        Expr condition = null;
        if (!check("SEMICOLON")) {
            condition = expression();
        }
        eat("SEMICOLON");
        Expr increment = null;
        if (!check("RIGHT_PAREN")) {
            increment = expression();
        }
        eat("RIGHT_PAREN");
        Stmt body = statement();
        if (increment != null) {
            body = new Block(Arrays.asList(body, new Expression(increment)));
        }
        if (condition == null) condition = new Literal(true);
        body = new While(condition, body);
        if (initializer != null) {
            body = new Block(Arrays.asList(initializer, body));
        }
        return body;
    }

    private Stmt ifStatement() {
        eat("LEFT_PAREN");
        Expr condition = expression();
        eat("RIGHT_PAREN");
        Stmt thenBranch = statement();
        Stmt elseBranch = null;
        if (match("ELSE")) {
            elseBranch = statement();
        }

        return new If(condition, thenBranch, elseBranch);
    }

    private Stmt printStatement() {
        Expr value = expression();
        eat("SEMICOLON");
        return new Print(value);
    }

    private Stmt varDeclaration() {
        Token name = eat("ID");
        Expr initializer = null;
        if (match("ATTR")) {
            //System.out.println(tokens.get(current));
            initializer = expression();
        }
        eat("SEMICOLON");
        return new Var(name, initializer);
    }

    private Stmt whileStatement() {
        eat("LEFT_PAREN");
        Expr condition = expression();
        eat("RIGHT_PAREN");
        Stmt body = statement();

        return new While(condition, body);
    }

    private Stmt expressionStatement() {
        Expr expr = expression();
        eat("SEMICOLON");
        return new Expression(expr);
    }

    private List<Stmt> block() {
        List<Stmt> statements = new ArrayList<>();

        while (!check("RIGHT_BRACE") && !isEnd()) {
            statements.add(declaration());
        }
        eat("RIGHT_BRACE");
        return statements;
    }

    private Expr assignment() {
        Expr expr = or();
        //System.out.println(tokens.get(current)+"--------------------");
        if (match("ATTR")) {
            Token equals = previous();
            // System.out.println(equals + "!!!!!");
            Expr value = assignment();
            if (expr instanceof Variable) {
                Token name = ((Variable) expr).name;
                return new Assign(name, value);
            } else if (expr instanceof Get) {
                Get get = (Get) expr;
                return new Set(get.object, get.name, value);
            }
            System.out.println("error ass");
        }

        return expr;
    }

    private Expr or() {
        Expr expr = and();
        while (match("OR")) {
            Token operator = previous();
            Expr right = and();
            expr = new Logical(expr, operator, right);
        }
        return expr;
    }

    private Expr and() {
        Expr expr = equality();
        while (match("AND")) {
            Token operator = previous();
            Expr right = equality();
            expr = new Logical(expr, operator, right);
        }
        return expr;
    }

    private Expr equality() {
        Expr expr = comparison();
        while (match("NOTEQ", "EQ")) {
            Token operator = previous();
            Expr right = comparison();
            expr = new Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr comparison() {
        Expr expr = term();
        while (match("MORE", "MOREOREQ", "LESS", "LESSOREQ")) {
            Token operator = previous();
            Expr right = term();
            expr = new Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr term() {
        Expr expr = factor();
        while (match("MINUS", "PLUS")) {
            Token operator = previous();
            Expr right = factor();
            expr = new Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr factor() {
        Expr expr = unary();
        while (match("SLASH", "STAR")) {
            Token operator = previous();
            Expr right = unary();
            expr = new Binary(expr, operator, right);
        }
        return expr;
    }

    private Expr unary() {
        if (match("NOT", "MINUS", "PLUS")) {
            Token operator = previous();
            Expr right = unary();
            return new Unary(operator, right);
        }
        return call();

    }

    private Expr finishCall(Expr callee) {
        List<Expr> arguments = new ArrayList<>();
        if (!check("RIGHT_PAREN")) {
            do {
                if (arguments.size() >= 255) {
                    System.out.println("no"); //255 аргументов для джвм
                }
                arguments.add(expression());
            } while (match("COMMA"));
        }
        Token paren = eat("RIGHT_PAREN");
        return new Call(callee, paren, arguments);
    }

    private Expr call() {
        Expr expr = primary();
        while (true) {
            if (match("LEFT_PAREN")) {
                expr = finishCall(expr);
            } else if (match("DOT")) {
                Token name = eat("ID");
                expr = new Get(expr, name);
            } else {
                break;
            }
        }
        return expr;
    }

    private Function function() {
        Token name = eat("ID");
        eat("LEFT_PAREN");
        List<Token> parameters = new ArrayList<>();
        if (!check("RIGHT_PAREN")) {
            do {
                if (parameters.size() >= 255) {
                    System.out.println("too many arg");
                }

                parameters.add(
                        eat("IDENTIFIER"));
            } while (match("COMMA"));
        }
        eat("RIGHT_PAREN");
        eat("LEFT_BRACE");
        List<Stmt> body = block();
        return new Function(name, parameters, body);
    }

    private Expr primary() {
        if (match("FALSE")) return new Literal(false);
        if (match("TRUE")) return new Literal(true);
        if (match("NONE")) return new Literal(null);
        if (match("NUMBER", "STRING")) {
            /*System.out.println(tokens.get(current)+"-    ------------");
            System.out.println(previous()+"---------         ----");*/
            return new Literal(previous().value);
        }
        if (match("ID")) {
            return new Variable(previous());
        }


        if (match("LEFT_PAREN")) {
            Expr expr = expression();
            eat("RIGHT_PAREN");
            return new Grouping(expr);
        }
        System.out.println("error primary");
        return null;
    }

    private boolean match(String... types) {
        for (String t : types) {
            if (tokens.get(current).type.equals(t)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token eat(String type) {
        if (check(type)) return advance();
        System.out.println("error eat");
        return null;
    }

    private boolean check(String type) {
        if (isEnd()) return false;
        return tokens.get(current).type.equals(type);
    }

    private Token advance() {
        if (!isEnd()) current++;
        return previous();
    }

    private boolean isEnd() {
        return tokens.get(current).type.equals("EOF");
    }


    private Token previous() {
        return tokens.get(current - 1);
    }

    public static void main(String[] args) {
        Expr expression;
        // Lexer lex = Lexer.LexInit(" 1+2+(3+(5*6)) ");
        //Lexer lex = Lexer.LexInit(" WHILE( 12 > 11){PRINT 12;} ");
        Lexer lex = Lexer.LexInit("FUNK v(){}");
        List<Token> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser parser = new Parser(toks);
        List<Stmt> statements = parser.parse();
        System.out.println(statements.toString());
    }
}
