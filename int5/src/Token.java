public class Token {
    public String type;
    public Object value;
    public String lexem;
    public Token(String type, Object value, String lexem){
        this.type=type;
        this.value =value;
        this.lexem=lexem;
    }

    @Override
    public String toString() {
        return "Token{" +
                type + '\'' +
                 lexem + '\'' +
                '}';
    }
}
