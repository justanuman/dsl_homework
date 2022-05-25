public class TokenStore  {
    public String type;
    public Object val;
    public TokenStore left;
    public TokenStore right;
    public int startPos;
    public int endPos;
    public TokenStore(String type, Object val){
        this.type=type;
        this.val =val;
    }
    public TokenStore(){
        this.type="none";
        this.val ="none";
    }

    @Override
    public String toString() {
        return   val.toString();
    }
    public String paren() {
        if ((left == null) && (right == null)) {
            return val.toString();
        } else {
            StringBuilder b = new StringBuilder();
            b.append("(");
            if (left != null) {
                b.append(left.paren()).append(" ");
            }
            b.append(val);
            if (right != null) {
                b.append(" ").append(right.paren());
            }
            b.append(")");
            return b.toString();
        }
    }
}
