import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.text.Normalizer;


public class ReadSent {
    private ArrayList<String> sentence;
    private ArrayList<String> goldTok;

    public ReadSent() throws IOException {
        this.sentence = sentSentence("final_na_examples.txt");
        this.goldTok = sentGoldToken("final_na_examples.txt");
    }

    public ArrayList<String> getGoldTok(){
        return this.goldTok;
    }
    public ArrayList<String> getSentence(){
        return this.sentence;
    }

    private ArrayList<String> sentSentence(String path) throws IOException {
        ArrayList<String> brut = new ArrayList<>();
        try (Stream<String> lines = Files.lines(
                Paths.get(path))){
            for (String line:
                    (Iterable<String>) lines::iterator){
                brut.addAll(List.of(line.split("@@@")[0]));
            }
        }
        return normalizeStr(brut);
    }

    private ArrayList<String> normalizeStr(ArrayList<String> sent){
        ArrayList<String> res = new ArrayList<>();
        for(String str: sent){
            str.replaceAll("◊", "|").replaceAll("[m]+[.]+", "mmm...").replaceAll("[ə]+[.]+", "əəə...");
            if(str.contains("BEGAIEMENT")){
                continue;
            }
            res.add(Normalizer.normalize(str, Normalizer.Form.NFKD));
        }return res;
    }

    private ArrayList<String> sentGoldToken(String path) throws IOException {
        ArrayList<String> brut = new ArrayList<>();
        try (Stream<String> lines = Files.lines(
                Paths.get(path))){
            for (String line:
                    (Iterable<String>) lines::iterator){
                brut.addAll(List.of(line.split("@@@")[1]));
            }
        }
        return normalizeStr(brut);
    }

}
