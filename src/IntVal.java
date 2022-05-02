public class IntVal extends ValueStore{
    public int val=1;
    private int res=0;
    public IntVal(String val){
        this.val=Integer.valueOf(val);
    }
    public IntVal(int val){
        this.val=val;
    }
    public int addition(IntVal right){
        res=val+right.val;
        return res;
    }
    public int substraction(IntVal right){
        res=val-right.val;
        return res;
    }
    public int multiplication(IntVal right){
        res=val*right.val;
        return res;
    }
    public int division(IntVal right){
        res=val/right.val;
        return res;
    }
}
