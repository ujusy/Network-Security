import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
public class DES_ECB {
    public static void main(String[] args) {
        String plainText= "Now is the time for";
        byte[] PlainText = null;
        byte[] key= { (byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67, (byte) 0x89, (byte) 0xAB,
                (byte) 0xCD, (byte) 0xEF};

        try {
            PlainText = plainText.getBytes("ASCII");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch blocks
            e.printStackTrace();
        }


        byte[] padding_result1 = ZERO(PlainText);
        System.out.println("Zero Padding : " + byteArrayToHex(DES_ECB(key, padding_result1)));
        System.out.println();

        byte[] padding_result2 = ANSI(PlainText);
        System.out.println("ANSI X9.23   : " + byteArrayToHex(DES_ECB(key, padding_result2)));
        System.out.println();

        byte[] padding_result3 = PKCS(PlainText);
        System.out.println("PKCS  5      : " + byteArrayToHex(DES_ECB(key, padding_result3)));
        System.out.println();

    }
    public static byte[] ZERO(byte[] PlainText){
        int padding_length = 8 - (PlainText.length % 8);
        if(PlainText.length%8 == 0){
            padding_length = 0;
        }
        byte[] padding_result = new byte[PlainText.length + padding_length];

        for (int i = 0; i < PlainText.length; i++)
            padding_result[i] = PlainText[i];
        for (int i = PlainText.length; i < padding_result.length; i++) //zero
            padding_result[i] = (byte) 0x00;

        return padding_result;

    }
    public static byte[] ANSI(byte[] PlainText){
        int padding_length = 8 - (PlainText.length % 8);

        byte[] padding_result = new byte[PlainText.length + padding_length];

        for (int i = 0; i < PlainText.length; i++)
            padding_result[i] = PlainText[i];

        for (int i = PlainText.length; i < padding_result.length-1; i++) //ansi
            padding_result[i] = (byte) 0x00;
        for (int i = padding_result.length-1; i < padding_result.length; i++)
            padding_result[i] = (byte) padding_length;

        return padding_result;

    }
    public static byte[] PKCS( byte[] PlainText){
        int padding_length = 8 - (PlainText.length % 8);

        byte[] padding_result = new byte[PlainText.length + padding_length];

        for (int i = 0; i < PlainText.length; i++)
            padding_result[i] = PlainText[i];

        for (int i = PlainText.length; i < padding_result.length; i++) //pkcs5
            padding_result[i] = (byte) padding_length;

        return padding_result;

    }
    public static byte[] DES_ECB(byte[] key, byte[] plainText) {
        SecretKey Key;
        Key = new SecretKeySpec(key, 0, key.length, "DES");	//키 생성
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance("DES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, Key);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int block_count = plainText.length / 8;
        byte[] result = new byte[block_count * 8];

        for (int i = 0; i < block_count; i++) {
            try {
                byte[] output = cipher.doFinal(Arrays.copyOfRange(plainText, i * 8, (i + 1) * 8));

                for (int j = 0; j < 8; j++)
                    result[j + i * 8] = output[j];

            } catch (IllegalBlockSizeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (BadPaddingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }
    public static String byteArrayToHex(byte[] a) {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for(byte b: a)
            sb.append(String.format("%02X ", b & 0xff));
        return sb.toString();
    }
}
