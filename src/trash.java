public class trash {
}
/*
* public class Parser {
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
    public Node ifExpr(){
        Node res=null;
        BinaryOpNode elseNode=null;
        //||(current.type).equals("fiToken")
        BinaryOpNode ifNode=null;
        BinaryOpNode ifstmntNode=null;
        if((current.type).equals("IFTOKEN"))
        {
            ifstmntNode = (BinaryOpNode) parser.expr();
            if (!(current.type).equals("thenToken")  ) {
                System.out.println("then something went wrong");
                parser.advance();}
            ifNode=(BinaryOpNode) parser.expr();
            if (!(current.type).equals("else")  ) {
                parser.advance();
                elseNode= (BinaryOpNode) parser.expr();
            }

            return new IfNode(ifstmntNode,ifNode,elseNode);
        }else{
            System.out.println("something went wrong");
        }
        return res;
    }
    public Node basic() {
        parser.advance();
        if ((current.type).equals("NUMBER") || (current.type).equals("ID")) {
            //System.out.println(" baza activated");
            currBasic = new BasicNode(current);
            parser.advance();
            return currBasic;
        } else if ((current.type).equals("LPRT")) {
            while (((parser.checkNext()).type).equals("LPRT")) {
                parser.advance();
            }
            BinaryOpNode temp = (BinaryOpNode) parser.expr();
            if ((current.type).equals("RPRT")) {
                //System.out.println(" () activated");
                parser.advance();
                return temp;
            }
        }else if((current.type).equals("IFTOKEN")){
            //System.out.println("if start");
            BinaryOpNode temp = (BinaryOpNode) parser.expr();
            if ((current.type).equals("thenToken")) {
                //System.out.println(" () activated");
                parser.advance();
                return temp;
            }
        }else if((current.type).equals("thenToken")){
            //System.out.println("if start");
            BinaryOpNode temp = (BinaryOpNode) parser.expr();
            if ((current.type).equals("fiToken")) {
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
            left = new BinaryOpNode(left, right, op,"matop");
        }
        return left;
    }

    public Node expr() {
        Node left = parser.basic();
        while ((current.type).equals("ADDITION") || (current.type).equals("SUBTRACTION") ||  (current.type).equals("ATTRIBUTION") || (current.val).equals(">")|| (current.val).equals("<")
                || (current.val).equals(">=")|| (current.val).equals("<=")|| (current.val).equals("<=")|| (current.val).equals("==")) {
            TokenStore op = current;
            Node right = parser.term();
            if ( (op.val).equals("=")) {
                left = new AttributionNode(left, right);
            }else if( (op.val).equals(">")|| (op.val).equals("<")
                    || (op.val).equals(">=")|| (op.val).equals("<=")|| (op.val).equals("<=")|| (op.val).equals("==")){
                left = new BinaryOpNode(left, right, op,"CompareOP");
            }else if((op.val).equals("AND")|| (op.val).equals("OR")){
                left = new BinaryOpNode(left, right, op,"LogicOP");
            }else if((op.val).equals("if")){

                left = parser.ifExpr();
            }
            else  {
            left = new BinaryOpNode(left, right, op,"matop");}
        }
        return left;
    }

    public Result parse() {
        Result res = new Result(parser.expr());
        return res;
    }

    public static void main(String[] args) {
        Lexer lex = Lexer.LexInit(" 7 * 4/2 ");
        //Lexer lex = Lexer.LexInit("if 2==3 then 2=2 fi ");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser par = Parser.Init(toks);
        System.out.println((par.parse()).toString());
    }
}
*/