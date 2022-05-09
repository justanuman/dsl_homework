public class BinaryOpNode extends Node {
    public Node left;
    public Node right;
    public TokenStore op;
    public String nodeType="binop";
    public BinaryOpNode(Node left, Node right, TokenStore op,String nodeType ){
        this.left=left;
        this.right=right;
        this.op=op;
        this.nodeType=nodeType;
    }

    public String getNodeType(){
        return nodeType;
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
