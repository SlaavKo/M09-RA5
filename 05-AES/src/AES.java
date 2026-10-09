import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    //public static Random rand;
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: "
                    + e.getLocalizedMessage());
            }
            System.out.println("-------------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] msgs = msg.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec ivXifra = generaIv();
        SecretKeySpec keyXifra = generaHash(clau);
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, keyXifra, ivXifra);

        byte[] xifrat = cipher.doFinal(msgs);
        byte[] res = new byte[MIDA_IV + xifrat.length];
        System.arraycopy(iv, 0, res, 0, MIDA_IV);
        System.arraycopy(xifrat, 0, res, MIDA_IV, xifrat.length);
        return res;
    }
    public static IvParameterSpec generaIv() {
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
    public static SecretKeySpec generaHash(String clau) throws Exception {
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(hash, ALGORISME_XIFRAT);
    }
    public static IvParameterSpec extreureIv(byte[] dades) {
        return new IvParameterSpec(Arrays.copyOfRange(dades, 0, MIDA_IV));
    }
    public  static byte[] getBytesXifrats(byte[] dades) {
        return Arrays.copyOfRange(dades, MIDA_IV, dades.length);
    }

    public static String desxifraAES(byte[] bIvMsgXifrat, String clau) throws Exception {
        IvParameterSpec ivDesXifra = extreureIv(bIvMsgXifrat);
        byte[] xifrat = getBytesXifrats(bIvMsgXifrat);
        SecretKeySpec keyDesXifra = generaHash(clau);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, keyDesXifra, ivDesXifra);
        byte[] desxifrat = cipher.doFinal(xifrat);
        return new String(desxifrat, StandardCharsets.UTF_8);
    }
}