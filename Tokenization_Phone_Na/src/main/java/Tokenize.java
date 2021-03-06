import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tokenize {
    private String path;
    private ArrayList<String> tokenized_Na;
    private ArrayList<String> goldToken;

    public Tokenize(String path) throws IOException {
        ReadPhone phone = new ReadPhone();
        ReadSent sentence = new ReadSent();
        this.goldToken = sentence.getGoldTok();
        this.path = String.valueOf(Paths.get(path).toAbsolutePath());
        this.tokenized_Na = tokenize_Na(sentence.getSentence(), phone.getSortedPhone());

    }

    public ArrayList<String> getTonkenized(){
        return this.tokenized_Na;
    }

    public ArrayList<String> tokenize_Na(ArrayList<String> sentLst, ArrayList<String> phonemes) throws IOException {
        ArrayList<String> res = new ArrayList<>();
        for(String sent: sentLst){
           // System.out.println(segmentPhone(sent));
            res.add(this.segmentPhone(sent, phonemes));
        }
        //System.out.println(res);
        return res;
    }

    public String segmentPhone(String sentence, ArrayList<String> phonemes) throws IOException {
        ArrayList<String> tokenized = new ArrayList<>();
        for(String word: this.sentWordLst(sentence)){
            word = word.replaceAll("[\\[](.*?)[\\]]", "").replaceAll("[<]",  "").replaceAll("[>]", "");
            while(!word.isEmpty()){
                word = this.extractedNextPhoneme(word, phonemes, tokenized);
            }
            tokenized.add("|");
        }//System.out.println(tokenized);
        return String.join(" ", tokenized);
    }

    private ArrayList<String> sentWordLst(String sent){
        return new ArrayList<>(List.of(sent.replaceAll("[\\s+\\n\\p{Punct}]", "").split("[|]")));
    }

    private String extractedNextPhoneme(String todo, ArrayList<String> phonemes, ArrayList<String> tokenize) {
        for(int i= this.counter(todo); i>0; i--) {
            if(todo.length()< i){
                return todo;
            }
            String tmp = todo.substring(0,i);
            if (phonemes.contains(tmp)) {
                tokenize.add(tmp);
                todo = todo.substring(i);
            } else if (i == 1) {
                tokenize.add(tmp);
                todo = todo.substring(i);
            }
        }return todo;

    }

    private Integer counter(String word) {
        if(word.length() >= 3) {
            return 3;
        } else {
            return word.length();
        }
    }


    public String printEval(){

        return ("program sorted phone: "+ this.tokenized_Na.size()+ "; gold sorted phone" + this.goldToken.size());
    }

}
