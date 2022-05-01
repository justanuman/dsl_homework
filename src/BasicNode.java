public class BasicNode extends Node implements Inode {
    public TokenStore tok;
    public String val;
    public String type;
    public String nodeType="basic";
    public BasicNode(TokenStore tok)
    {
        this.tok=tok;
        val=tok.val;
        type= tok.type;
    }
    public String getNodeType(){
        return "baseNode";
    }
    @Override
    public String toString() {
        return val;
    }
}
