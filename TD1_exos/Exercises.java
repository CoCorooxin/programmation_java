public class Exercises {
    public static int count_Vowels(String str){
        str = str.toLowerCase();
        int numberVoy = 0;
        //for(int i = 0; i < str.length(); i++) {
        for(char c: str.toCharArray()){
            //"" + what --- conversion de what en string
            //if("aeiou".contains("" + str.charAt(i))){
            if("aeiou".contains("" + c )){
                numberVoy += 1;
            }
        }
        return numberVoy;
    }

    public static void print(){
        String star = "*";
        String cross = "+";
        String space = " ";
        for(int i = 0 ; i <= 6; i++){
            if ((i%2)==0){
                String result = space.repeat(6-i)+cross.repeat(i*2);
                System.out.println(result);
            }
            else{
                String result = space.repeat(6-i)+ star.repeat(i*2);
                System.out.println(result);
            }
        }
    }
}
