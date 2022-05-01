public class BinaryOpNode extends Node {
    public Node left;
    public Node right;
    public TokenStore op;
    public String nodeType="binop";
    public BinaryOpNode(Node left, Node right, TokenStore op){
        this.left=left;
        this.right=right;
        this.op=op;
    }
    public String getNodeType(){
        return "binNode";
    }
    public Node getLeft(){
        return left;
    }
    public Node getRight(){
        return right;
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
