
public class Transposition {
    public static void main(String[] args){
        String PlainText = "Common sense is not so common";
        String rst = transposition_encrypt(PlainText, 8);

        System.out.println(PlainText);
        System.out.println(rst);
    }
    public static String transposition_encrypt(String plain, int Key){
        int col = 0;
        int a = 0;
        String result = "";
        String row;

        col = plain.length()/Key;
        if( plain.length()%Key != 0 )
            col += 1;
        char[] Plain_Arr = plain.toCharArray();
        char[][] Plain_Arr2 = new char[col][Key];

        for(int i=0;i<col;i++){
            for(int j=0;j<Key;j++){
                if(a<plain.length()){
                    Plain_Arr2[i][j]=Plain_Arr[a++];
                }else break;
            }
        }
        for(int i=0;i<Key;i++){
            for(int j=0;j<col;j++){
                if(Plain_Arr2[j][i]!=0)
                    result += Plain_Arr2[j][i];
            }
        }

        return result;
    }
}

