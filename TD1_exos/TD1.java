import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        char[] ch = str.toCharArray();
        String res = "";
        for(int i = ch.length-1 ; i >= 0; i--){
            res += ch[i];
        }
        return res;
    }
    public static String expandNumber_boucle(String str) {
        StringBuilder num_of_expansion = new StringBuilder();
        StringBuilder res_stringB = new StringBuilder();
        char todo;
        int position;
        int len_str = str.length();
        int new_length = 0;
        for (int i = 0; i< len_str; i++){
            todo = str.charAt(i);
            position = i - num_of_expansion.length()-1;
            if(!Character.isDigit(todo)){
                res_stringB.append(todo);
                if (!num_of_expansion.isEmpty()){
                    int j;
                    for(j = 0 ; j< Integer.parseInt(num_of_expansion.toString())-1; j++){
                        new_length = res_stringB.length();
                        res_stringB.insert(new_length-2, str.charAt(position));
                    }num_of_expansion.setLength(0);
                }
            } else {
                num_of_expansion.append(todo);
            }if(i == len_str-1 & !num_of_expansion.isEmpty()){
                int m;
                for(m = 0; m<Integer.parseInt(num_of_expansion.toString())-1; m++){
                    res_stringB.append(str.charAt(position));
                }
            }
        }
        return res_stringB.toString();
    }
    public static String expandNumber_regex(String str) {
        String[] subStrings;
        StringBuilder res_stringB = new StringBuilder();
        subStrings = str.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
        int len_str = subStrings.length;
        for (int i = 0; i< len_str; i++){
            int num_of_expansion;
            if(subStrings[i].matches("\\d+")& i!=0){
                num_of_expansion = Integer.parseInt(subStrings[i])-1;
                String todo = subStrings[i - 1];
                todo = todo.split("")[todo.length()-1];
                String repeat_str = String.join("", Collections.nCopies(num_of_expansion, todo));
                res_stringB.append(repeat_str);
            } else {
                res_stringB.append(subStrings[i]);
            }
        }return res_stringB.toString();
    }
}

