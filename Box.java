public class Box {
    public static void main(String[] args){
        String PlainText = "ABCDEFGH";
        int[] index1 = {2, 6, 3, 1, 4, 8, 5, 7};
        int[] index2 = {4, 1, 3, 5, 7, 2, 8, 6};
        String rst1 = PBox(PlainText,index1);
        String rst2 = PBox(rst1, index2);

        System.out.println(rst1);
        System.out.println(rst2);

    }
    public static String PBox(String P, int[] index){
        String result ="";
        if(P.length() !=8)
            return "err";

        for(int a:index){
            result +=P.charAt(a-1);
        }
        return  result;
    }
}

