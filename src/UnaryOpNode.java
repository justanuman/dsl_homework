public class UnaryOpNode extends Node{
    public Node right;
    public TokenStore op;
    public String nodeType="unop";
    public UnaryOpNode(TokenStore op,Node left){
        this.right=left;
        this.op=op;
    }

    @Override
    public String toString() {
        return "{" + op +
                right +
                '}';
    }
}
