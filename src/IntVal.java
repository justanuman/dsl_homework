public class IntVal {
    private int val=0;
    private int res=0;
    public IntVal(){
    }
    public int addition(TokenStore tok,TokenStore tok2){
        res=Integer.valueOf(tok.val)+Integer.valueOf(tok2.val);
        return res;
    }
    public int substraction(TokenStore tok,TokenStore tok2){
        res=Integer.valueOf(tok.val)-Integer.valueOf(tok2.val);
        System.out.println("works");
        return res;
    }
    public int multiplication(TokenStore tok,TokenStore tok2){
        res=Integer.valueOf(tok.val)*Integer.valueOf(tok2.val);
        return res;
    }
    public int division(TokenStore tok,TokenStore tok2){
        res=Integer.valueOf(tok.val)/Integer.valueOf(tok2.val);
        return res;
    }
}
