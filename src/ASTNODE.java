public class ASTNODE {
    public String type;
    public ASTNODE left=null;
    public ASTNODE right=null;
    public ASTNODE middle=null;
    public String val=null;
    public TokenStore  op=null;
    public ASTNODE(){}
    public  ASTNODE(ASTNODE left, ASTNODE right, TokenStore  op){
        this.type="BinaryOP";
        this.left=left;
        this.right=right;
        this.op=op;
    }
    public  ASTNODE(ASTNODE right, TokenStore  op){
        this.type="UnaryOP";
        this.op=op;
        this.right=right;
    }
    public ASTNODE(String val)
    {
        this.type="Atom";
        this.val=val;
    }


    @Override
    public String toString() {
        String out="{";
        if(left!=null){
            out=out+left.toString();
        }
        if(op!=null){
            out=out+op.val;
        }
        if(right!=null){
            out=out+right.toString();
        }
        if(val!=null){
            out=out+val;
        }
        out=out+"}";
        return out;
    }
}
