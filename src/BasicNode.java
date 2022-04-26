public class BasicNode extends Node {
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

    @Override
    public String toString() {
        return val;
    }
}
