public class BinaryOpNode extends Node {
    public Node left;
    public Node right;
    public TokenStore op;
    public BinaryOpNode(Node left, Node right, TokenStore op){
        this.left=left;
        this.right=right;
        this.op=op;
    }

    @Override
    public String toString() {
        return "{" +
                 left +
                 op +
                 right +

                '}';
    }
}
