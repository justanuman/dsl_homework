public class IfNode extends Node{
    public BinaryOpNode ifStatement;
    public BinaryOpNode activity;
    public BinaryOpNode elseactivity;
    public String nodeType="ifNode";
    public IfNode(BinaryOpNode ifStatement, BinaryOpNode activity,BinaryOpNode elseactivity ){
        this.ifStatement=ifStatement;
        this.activity=activity;
        this.elseactivity=elseactivity;
    }

    public String getNodeType(){
        return nodeType;
    }

    public BinaryOpNode getActivity() {
        return activity;
    }

    public BinaryOpNode getElseactivity() {
        return elseactivity;
    }

    public BinaryOpNode getIfStatement() {
        return ifStatement;
    }

    @Override
    public String toString() {
        return "IfNode{" +
                "ifStatement=" + ifStatement +
                ", activity=" + activity +
                ", elseactivity=" + elseactivity +
                ", nodeType='" + nodeType + '\'' +
                '}';
    }
}
