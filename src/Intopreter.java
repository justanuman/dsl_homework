import java.util.List;

public class Intopreter {
    private Result ast;
    private Node currNode;
    private static Intopreter intopreter;

    public static Intopreter initIntpr(Result ast)
    {
        intopreter=  new Intopreter(ast);
        return intopreter;
    }
    private Intopreter(Result ast)
    {
        this.ast=ast;
        this.currNode=ast.getNode();
    }
    public void start(){
        intopreter.checkNode(currNode);
    }
    public TokenStore checkNode(Node current){
        if (current.getNodeType()=="baseNode"){
            return intopreter.proccessBasic((BasicNode) current);
        }else if (current.getNodeType()=="binNode"){
            intopreter.proccessBinop((BinaryOpNode) current);
        }
        return null;
    }
    public void proccessBinop(BinaryOpNode current){
        IntVal intv = new IntVal();
        //System.out.println(current.toString());
        int res=0;
        TokenStore left= intopreter.checkNode(current.getLeft());
        TokenStore right= intopreter.checkNode(current.getRight());
        String op =current.op.val;
        if(op.equals("+")){
            res= intv.addition(left,right);
        }
        else if(op.equals("-"))
        {
            res= intv.substraction(left,right);
        }
        else if(op == "*")
        {
            res= intv.multiplication(left,right);
        }
        else if(op == "/")
        {
            res= intv.division(left,right);
        }
        System.out.println(res);

    }
    public TokenStore proccessBasic(BasicNode current){
        return current.tok;
    }
    public void processUno(){}
    public static void main(String[] args) {
        Lexer lex = Lexer.LexInit("5+6");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser par = Parser.Init(toks);
        Intopreter intpr = Intopreter.initIntpr(par.parse());
        intpr.start();
    }
}
