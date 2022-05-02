

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {
    private StringBuilder input = new StringBuilder();
    private String lexem;
    private boolean EOS = false;
    private String errorMessage = "";
    private List<TokenStore> tokenStoreList= new ArrayList<>();
    private List<Pattern> listofpatterns= new ArrayList<Pattern>();
    private List<String> listofnames= new ArrayList<>();
    private static Lexer lexer;
    public static Lexer LexInit(String input){
        lexer= new Lexer( input);
        return lexer;
    }
    private Lexer(String input){
        listofpatterns.add(Pattern.compile("^" + "AND"));
        listofnames.add("AND");
        listofpatterns.add(Pattern.compile("^" + "OR"));
        listofnames.add("OR");
        listofpatterns.add(Pattern.compile("^" + "TRUE"));
        listofnames.add("TRUETOKEN");
        listofpatterns.add(Pattern.compile("^" + "FALSE"));
        listofnames.add("TOKEN");
        listofpatterns.add(Pattern.compile("^" + "not"));
        listofnames.add("NOTTOKEN");
        listofpatterns.add(Pattern.compile("^" + "=="));
        listofnames.add("EQUALSTOKEN");
        listofpatterns.add(Pattern.compile("^" + "if"));
        listofnames.add("IFTOKEN");
        listofpatterns.add(Pattern.compile("^" + "\\d+(\\.\\d+)?"));
        listofnames.add("NUMBER");
        listofpatterns.add(Pattern.compile("^" + "\\w+"));
        listofnames.add("ID");
        listofpatterns.add(Pattern.compile("^" + "="));
        listofnames.add("ATTRIBUTION");
        listofpatterns.add(Pattern.compile("^" + "\\+"));
        listofnames.add("ADDITION");
        listofpatterns.add(Pattern.compile("^" + "-"));
        listofnames.add("SUBTRACTION");
        listofpatterns.add(Pattern.compile("^" + "\\*"));
        listofnames.add("MULTIPLICATION");
        listofpatterns.add(Pattern.compile("^" + "/"));
        listofnames.add("DIVISION");
        listofpatterns.add(Pattern.compile("^" + "\"[^\"]+\""));
        listofnames.add("STRING");
        listofpatterns.add(Pattern.compile("^" + " "));
        listofnames.add("BLANK");
        listofpatterns.add(Pattern.compile("^" + "\\("));
        listofnames.add("LPRT");
        listofpatterns.add(Pattern.compile("^" + "\\)"));
        listofnames.add("RPRT");
        listofpatterns.add(Pattern.compile("^" + ">"));
        listofnames.add("MORETOKEN");
        listofpatterns.add(Pattern.compile("^" + "<"));
        listofnames.add("LESSTOKEN");
        listofpatterns.add(Pattern.compile("^" + ">="));
        listofnames.add("MOREOREQTOKEN");
        listofpatterns.add(Pattern.compile("^" + "<="));
        listofnames.add("LESSOREQTOKEN");

        this.input.append(input);
    }

    public void moveAhead() {
        if (EOS) {
            return;
        }
        if (input.length() == 0) {
            EOS = true;
            return;
        }
        if (findTokenFromList()) {
            return;
        }
        EOS = true;
        if (input.length() > 0) {
            errorMessage = "Unexpected symbol: '" + input.charAt(0) + "'";
        }
    }
    private boolean findTokenFromList(){
        for (int i=0; i<listofpatterns.size();i++){
            Matcher matcher = ((listofpatterns.get(i)).matcher(input));
            if(matcher.find()){
                lexem = input.substring(0, matcher.end());
                TokenStore tok = new TokenStore(listofnames.get(i), lexem);
                if(!(tok.type).equals("BLANK"))
                {
                    tokenStoreList.add(tok);
                }
                input.delete(0, matcher.end());
                return true;
            }
        }
        return false;
    }


    public boolean isSuccess() {
        return errorMessage.isEmpty();
    }
    public String errorMessage() {
        return errorMessage;
    }
    public boolean isOver() {
        return EOS;
    }

    public List<TokenStore> getTokens() {
        return tokenStoreList;
    }

    public List<TokenStore> startLexAnal(){
        while (!lexer.isOver()) {
            lexer.moveAhead();
        }
        if (!lexer.isSuccess()) {
            System.out.println(lexer.errorMessage());
            return null;
        }else{
            List<TokenStore> toks=lexer.getTokens();
            toks.add(new TokenStore("EOF","EOF"));
            return toks;

        }

    }
    public static void main(String[] args) {
        Lexer lexer = new Lexer("as1 = 2 - (3 + 5*7)");
        while (!lexer.isOver()) {
            lexer.moveAhead();
        }
        List<TokenStore> toks=  lexer.getTokens();
        for(int i=0;i<toks.size();i++){
            System.out.println(toks.get(i)+(toks.get(i)).type);
        }
        System.out.println(lexer.errorMessage);
    }

}

