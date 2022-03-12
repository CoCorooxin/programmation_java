import java.io.IOException;

public class TokenziationDrive {
    public static void main(String[] agr) throws IOException, NonExistPhoneException {

        ReadPhone phone = new ReadPhone();
        //System.out.println(phone.sortedPhone);
        //System.out.println(phone.phoneUnicodeMap);
        ReadSent na = new ReadSent();
        //System.out.println(sent.sentence);
        Tokenize na_Exemple = new Tokenize("final_na_examples.txt");
        //System.out.println(na_Exemple.getTonkenized());

        //String s ="ṽ̩˥";
        // System.out.println(s.length());
        System.out.println(na_Exemple.printEval());
    }


}
