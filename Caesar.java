
public class Caesar {
    public static void main(String[] args){
        String PlanText = "The quick brown fox jumps over the lazy dog";
        String CiperText = Caesar(PlanText, 2, "encrypt");
        String rst = Caesar(CiperText, 2, "decrypt");

        System.out.println(PlanText);
        System.out.println(CiperText);
        System.out.println(rst);
    }
    public static String Caesar(String plain, int Key, String mode) {
        String word = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Plain = plain.toUpperCase();
        String result = "";

        if (mode.equals("decrypt"))
            Key = (word.length() - Key) % word.length();
        else if (mode.equals("encrypt"))
            Key = Key % word.length();
        else
            return "err";

        char[] plain_Arry = Plain.toCharArray();
        for(char s:plain_Arry) {
            if(s==' '){
                result +=s;
            } else{
                int num = (word.indexOf(s) + Key) % word.length();
                result += word.charAt(num);
            }
        }
     return  result.toLowerCase();
    }
    }
