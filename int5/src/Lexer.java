

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lexer {
    private StringBuilder input = new StringBuilder();
    private String lexem;
    private boolean EOS = false;
    private String errorMessage = "";
    private List<Token> TokenStoreList = new ArrayList<>();
    private List<Pattern> listofpatterns = new ArrayList<Pattern>();
    private List<String> listofnames = new ArrayList<>();
    private static Lexer lexer;

    public static Lexer LexInit(String input) {
        lexer = new Lexer(input);
        return lexer;
    }
    private Lexer(String input) {
        listofpatterns.add(Pattern.compile("^" + " "));
        listofnames.add("BLANK");

        listofpatterns.add(Pattern.compile("^" + "!"));
        listofnames.add("NOT");

        listofpatterns.add(Pattern.compile("^" + "\n"));
        listofnames.add("EOS");

        listofpatterns.add(Pattern.compile("^" + "VAR"));
        listofnames.add("VAR");

        listofpatterns.add(Pattern.compile("^" + "PRINT"));
        listofnames.add("PRINT");

        listofpatterns.add(Pattern.compile("^" + "AND"));
        listofnames.add("AND");

        listofpatterns.add(Pattern.compile("^" + "OR"));
        listofnames.add("OR");

        listofpatterns.add(Pattern.compile("^" + "IF"));
        listofnames.add("IF");

        listofpatterns.add(Pattern.compile("^" + "ELSE"));
        listofnames.add("ELSE");

        listofpatterns.add(Pattern.compile("^" + "TRUE"));
        listofnames.add("TRUE");

        listofpatterns.add(Pattern.compile("^" + "FALSE"));
        listofnames.add("FALSE");

        listofpatterns.add(Pattern.compile("^" + "FUNK"));
        listofnames.add("FUNK");

        listofpatterns.add(Pattern.compile("^" + "FOR"));
        listofnames.add("FOR");

        listofpatterns.add(Pattern.compile("^" + "WHILE"));
        listofnames.add("WHILE");

        listofpatterns.add(Pattern.compile("^" + "NONE"));
        listofnames.add("NONE");

        listofpatterns.add(Pattern.compile("^" + "RETURN"));
        listofnames.add("RETURN");

        listofpatterns.add(Pattern.compile("^" + "THIS"));
        listofnames.add("THIS");

        listofpatterns.add(Pattern.compile("^" + ">"));
        listofnames.add("MORE");

        listofpatterns.add(Pattern.compile("^" + "<"));
        listofnames.add("LESS");

        listofpatterns.add(Pattern.compile("^" + ">="));
        listofnames.add("MOREOREQ");

        listofpatterns.add(Pattern.compile("^" + "<="));
        listofnames.add("LESSOREQ");

        listofpatterns.add(Pattern.compile("^" + "=="));
        listofnames.add("EQ");

        listofpatterns.add(Pattern.compile("^" + "!="));
        listofnames.add("NOTEQ");

        listofpatterns.add(Pattern.compile("^" + "="));
        listofnames.add("ATTR");

        listofpatterns.add(Pattern.compile("^" + "\\("));
        listofnames.add("LEFT_PAREN");

        listofpatterns.add(Pattern.compile("^" + "\\)"));
        listofnames.add("RIGHT_PAREN");

        listofpatterns.add(Pattern.compile("^" + "\\{"));
        listofnames.add("LEFT_BRACE");

        listofpatterns.add(Pattern.compile("^" + "\\}"));
        listofnames.add("RIGHT_BRACE");

        listofpatterns.add(Pattern.compile("^" + "\\["));
        listofnames.add("LEFT_BRACKET");

        listofpatterns.add(Pattern.compile("^" + "\\]"));
        listofnames.add("RIGHT_BRACKET");

        listofpatterns.add(Pattern.compile("^" + ","));
        listofnames.add("COMMA");

        listofpatterns.add(Pattern.compile("^" + "\\."));
        listofnames.add("DOT");

        listofpatterns.add(Pattern.compile("^" + "\\+"));
        listofnames.add("PLUS");

        listofpatterns.add(Pattern.compile("^" + "-"));
        listofnames.add("MINUS");

        listofpatterns.add(Pattern.compile("^" + "\\*"));
        listofnames.add("STAR");

        listofpatterns.add(Pattern.compile("^" + "/"));
        listofnames.add("SLASH");

        listofpatterns.add(Pattern.compile("^" + ";"));
        listofnames.add("SEMICOLON");

        listofpatterns.add(Pattern.compile("^" + "\"[^\"]+\""));
        listofnames.add("STRING");

        listofpatterns.add(Pattern.compile("^" + "\\d+(\\.\\d+)?"));
        listofnames.add("NUMBER");

        listofpatterns.add(Pattern.compile("^" + "\\w+"));
        listofnames.add("ID");
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

    private boolean findTokenFromList() {
        Token tok;
        for (int i = 0; i < listofpatterns.size(); i++) {
            Matcher matcher = ((listofpatterns.get(i)).matcher(input));
            if (matcher.find()) {
                lexem = input.substring(0, matcher.end());
                if (listofnames.get(i).equals("NUMBER")) {
                    tok = new Token(listofnames.get(i), Integer.valueOf(lexem),lexem);
                } else if(listofnames.get(i).equals("TRUE")){
                    tok = new Token(listofnames.get(i), true,lexem);
                }else if(listofnames.get(i).equals("FALSE")){
                    tok = new Token(listofnames.get(i), false,lexem);
                }else {
                    tok = new Token(listofnames.get(i), lexem,lexem);
                }
                if (!((tok.type).equals("BLANK")&&!(tok.type).equals("EOS"))) {
                    TokenStoreList.add(tok);
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

    public List<Token> getTokens() {
        return TokenStoreList;
    }

    public List<Token> startLexAnal() {
        while (!lexer.isOver()) {
            lexer.moveAhead();
        }
        if (!lexer.isSuccess()) {
            System.out.println(lexer.errorMessage());
            return null;
        } else {
            List<Token> toks = lexer.getTokens();
            toks.add(new Token("EOF", "EOF","EOF"));
            return toks;

        }

    }

    public static void main(String[] args) {
        Lexer lexer = new Lexer("FUNK v(){}");
        while (!lexer.isOver()) {
            lexer.moveAhead();
        }
        List<Token> toks = lexer.getTokens();
        for (int i = 0; i < toks.size(); i++) {
            System.out.println(toks.get(i) + (toks.get(i)).type);
        }
        System.out.println(lexer.errorMessage);
    }

}

