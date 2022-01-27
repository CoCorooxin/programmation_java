public class TD1 {

    public static String removeSpaces(String str){
        String res;
        res = str.replaceAll("\\s+", "");
        return res;
    }

    public static String inverseWithStringBuilder(String str){
        StringBuilder build = new StringBuilder(str);
        build.reverse();
        return build.toString();

    }
    public static String inverseWithIteration(String str){
        char ch[] = str.toCharArray();
        String res = "";
        for(int i = ch.length-1 ; i >= 0; i--){
            res += ch[i];
        }
        return res;
    }

}
