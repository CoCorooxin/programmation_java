import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ReadPhone {
    private ArrayList<String> sortedPhone;
    private String content;
    private HashMap<String, ArrayList<Integer>> phoneUnicodeMap;
    private ArrayList<ArrayList<Integer>> CodePts;

    public ReadPhone() throws IOException {
        this.content = readContent("na_phonemes.txt");
        this.sortedPhone = PhoneSorted();
        this.phoneUnicodeMap = readInventory();
        this.CodePts = PhoneToCodePts();
    }

    public ArrayList<String> getSortedPhone(){
        return this.sortedPhone;
    }

    public String readContent(String filename)throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    public HashMap<String, ArrayList<Integer>> readInventory(){
        HashMap<String, ArrayList<Integer>> inventory = new HashMap<>();
        for (String line: this.content.split("\n")){
            String phone = line.split(":")[0].trim();
            ArrayList<Integer> unicode = getCodepoints(phone);
            inventory.putIfAbsent(phone, unicode);
        }
        return inventory;
    }

    public ArrayList<String> PhoneSorted(){
        ArrayList<String> phoneLst = new ArrayList<>(readInventory().keySet());
        Collections.sort(phoneLst, Comparator.comparing(String::length).reversed());
        return phoneLst;
    }

    public ArrayList<ArrayList<Integer>> PhoneToCodePts(){
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(String phone: sortedPhone){
            res.add(phoneUnicodeMap.get(phone));
        }return res;
    }
    private ArrayList<Integer> getCodepoints(String phone){
        ArrayList<Integer> res = new ArrayList<>();
        for(int codePt: phone.codePoints().toArray()){
            res.add(codePt);
        }return res;
    }


}
