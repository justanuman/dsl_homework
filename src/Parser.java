import java.util.ArrayList;
import java.util.List;

/*
  stmnt = expr;
  грамматика в2 си минус минус
  expr = atom unaryOP BinaryOP group
  atom = number str true false none
  group = "(" exp ")"
  unaryOP= !|- expr
  binaryOP = expr op expr
  operations = operators
  WHILE while (expr) stament;
  PRINT print expr;


  грамматика в3
  выражения
        attribution   (ID = attribution)
        OR            AND ( "OR" AND )
        AND           equality ( "and" equality )
        equality      comparison ( ( != || == ) comparison )
        comparison    term ( ( > || >= || < || <= ) term )
        term          factor ( ( "-" || "+" ) factor )
        factor        unaryOP ( ( / || * ) unaryOP )
        unaryOP       ( ! || - ) unary || call
        call          atom ( ( args ) || . ID )
        atom          TRUE || FALSE || none  || this
               || NUMBER || STRING || IDENTIFIER || "(" expression ")"
 */
public class Parser {
    private List<TokenStore> toks = new ArrayList<>();
    private int pos = -1;
    private TokenStore current;
    private ASTNODE currentNode;
    private ASTNODE resultTree;
    private static Parser parser;
    public static Parser Init(List<TokenStore> toks) {
        parser = new Parser(toks);
        return parser;
    }

    private Parser(List<TokenStore> toks) {
        this.toks = toks;
    }

    public TokenStore advance() {
        if (pos < toks.size() - 1) {
            pos += 1;
            current = toks.get(pos);
        }
        return current;
    }
    public TokenStore goBack() {
        if (pos > 0) {
            pos -= 1;
            current = toks.get(pos);
        }
        return current;
    }
    public TokenStore checkNext() {
        if (pos < toks.size() - 1) {
             return toks.get(pos+1);
        }
        return null;
    }




    public static void main(String[] args) {
        Lexer lex = Lexer.LexInit(" 1+2+3+(5*6) ");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser par = Parser.Init(toks);
        //System.out.println(parser.parse().toString());
        //System.out.println();
    }

    private  List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!current.type.equals("EOF")) {
            statements.add(declaration());
        }
}
