import java.util.ArrayList;
import java.util.List;

/*
* basic = int ()
* expr  =term ((PLUS|MINUS) term)*
  term = basic ((MUL|DIV) basic)*
  basic =   + - basic
  basic = ( expr )
 */
public class Parser {
    private List<TokenStore> toks = new ArrayList<>();
    private int pos = -1;
    private TokenStore current;
    private Node currBasic;
    private Node currTerm;
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

    public Node basic() {
        parser.advance();
        if ((current.type).equals("NUMBER")) {
            //System.out.println(" baza activated");
            currBasic = new BasicNode(current);
            parser.advance();
            return currBasic;}
        else if ((current.type).equals("LPRT")) {

            BinaryOpNode temp = (BinaryOpNode) parser.expr();
            if ((current.type).equals("RPRT")) {
                //System.out.println(" () activated");
                parser.advance();
                return temp;

            }
        }
        System.out.println("error something");
        return null;
    }

    public Node term() {
        Node left = parser.basic();
        while ((current.type).equals("MULTIPLICATION") || (current.type).equals("DIVISION")) {
            TokenStore op = current;
            Node right = parser.basic();
            left = new BinaryOpNode(left, right, op);
        }
        return left;
    }

    public Node expr() {
        Node left = parser.basic();
        while ((current.type).equals("ADDITION") || (current.type).equals("SUBTRACTION")) {
            TokenStore op = current;
            Node right = parser.term();
            left = new BinaryOpNode(left, right, op);
        }
        return left;
    }

    public Node parse() {
        Result res = new Result(parser.expr());
        return res;


    }

    public static void main(String[] args) {
        Lexer lex = Lexer.LexInit("1 + (2+3)*5 + ((8-9))");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser par = Parser.Init(toks);
        System.out.println((par.parse()).toString());
    }
}
