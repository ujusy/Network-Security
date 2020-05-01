import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static void main(String[] args) {
        String PlainText = "SHA-2 is a set of cryptographic hash functions " +
                "(SHA-224, SHA-256, SHA-384, SHA-512, SHA-512/224, SHA-512/256) designed by the U.S. " +
                "National Security Agency (NSA) and published in 2001 by the NIST as a U.S. Federal Information " +
                "Processing Standard (FIPS). SHA stands for Secure Hash Algorithm. SHA-2 includes a significant number of changes from its predecessor," +
                " SHA-1. SHA-2 currently consists of a set of six hash functions with digests that are 224, 256, 384 or 512 bits.";

        String hashmode[]={"MD5","SHA-1","SHA-256","SHA-512"};

        for(String str:hashmode){
            System.out.println(str+" : "+ hash(PlainText,str));
        }


    }

    public static String hash(String PlainText,String hash){
        String result ="";
        try {
            MessageDigest md = MessageDigest.getInstance(hash);
            md.update(PlainText.getBytes());
            byte Data[] = md.digest();
            result = BytetoString(Data);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            result = null;
        }
        return result;

    }
    private  static String BytetoString(byte[] bytes){
        StringBuffer bf = new StringBuffer();
        for(int i=0; i<bytes.length; i++)
        {
            bf.append(Integer.toString((bytes[i]&0xff) +0x100, 16).substring(1));
        }

        return bf.toString();
    }
}
