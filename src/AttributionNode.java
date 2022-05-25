public class AttributionNode extends Node{
    public Node varName;
    public Node varVal;

    public String nodeType="value";
    public AttributionNode(Node left, Node val){
        this.varName=left;
        this.varVal=val;
    }
    public String getNodeType(){
        return "value";
    }
    @Override
    public String toString() {
        return varName +"="+
                varVal;
    }
}
