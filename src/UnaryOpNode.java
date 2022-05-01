public class UnaryOpNode extends Node implements Inode{
    public Node right;
    public TokenStore op;
    public String nodeType="unop";
    public UnaryOpNode(TokenStore op,Node left){
        this.right=left;
        this.op=op;
    }
    public String getNodeType(){
        return "unoNode";
    }
    @Override
    public String toString() {
        return "{" + op +
                right +
                '}';
    }
}
