import java.util.List;

public class Intopreter {
    /*private Result ast;
    private Node currNode;
    private static Intopreter intopreter;
    private int res=0;
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

    public IntVal checkNode(Node current){
        System.out.println(current.getNodeType());
        if (current.getNodeType()=="baseNode"){
            System.out.println(current.toString());
            return intopreter.proccessBasic((BasicNode) current);
        }else if (current.getNodeType()=="binNode"){
            System.out.println(current.toString());
            return intopreter.proccessBinop((BinaryOpNode) current);
        }
        return null;
    }
    public IntVal proccessBinop(BinaryOpNode current){
        //System.out.println(current.toString());
        int res=0;
        IntVal left= intopreter.checkNode(current.getLeft());
        IntVal right= intopreter.checkNode(current.getRight());
        String op =current.op.val;
        if(op.equals("+")&& left!=null){
            res= left.addition(right);
        }
        else if(op.equals("-") && left!=null)
        {
            res=left.substraction(right);
        }
        else if(op.equals("*") && left!=null)
        {
            res= left.multiplication(right);
        }
        else if(op.equals("/") && left!=null)
        {
            res= left.division(right);
        }
        this.res=res;
        return new IntVal(res);
        //System.out.println(this.res);

    }
    public IntVal proccessBasic(BasicNode current){
        System.out.println(current.toString());
        return new IntVal(current.tok.val);
    }

    public int getRes() {
        return res;
    }

    public void processUno(){}
    public static void main(String[] args) {
        Lexer lex = Lexer.LexInit("(1 +  (2+3)*4) + (5-6)");
        List<TokenStore> toks = lex.startLexAnal();
        System.out.println(toks.toString());
        Parser par = Parser.Init(toks);
        //System.out.println((par.parse()).toString());
        //Intopreter intpr = Intopreter.initIntpr(par.parse());
       // intpr.start();
        //System.out.println(intpr.getRes());
    }*/
}
