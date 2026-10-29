import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {
    public static int npass = 0;
    private static String charset = "abcdefABCDEF1234567890!";
    private static final int maxLength = 6;

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";

        Hashes h = new Hashes();

        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for(int i=0; i< aHashes.length; i++){
            System.out.printf("=========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("-------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("-------------------------\n\n");
        }
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        npass = 0;
        char buffer[] = new char[maxLength];
        return forcaBrutaRec(alg, hash, salt, buffer, 0);
    }

    public String getSHA512AmbSalt(String password, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte bytes[] = md.digest(password.getBytes());

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String password, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 1000, 512);
        SecretKeyFactory fact = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte bytes[] = fact.generateSecret(spec).getEncoded();

        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    public String forcaBrutaRec(String alg, String hash, String salt, char[] buf, int len) throws Exception {
        for(int i = 0; i < charset.length(); i++) {
            buf[len] = charset.charAt(i);
            String test = new String(buf, 0, len + 1);

            npass++;

            String testHash;
            if (alg.equals("SHA-512")) testHash = getSHA512AmbSalt(test, salt);
            else testHash = getPBKDF2AmbSalt(test, salt);

            if (testHash.equals(hash)) return test;

            if (len + 1 < maxLength) {
                String res = forcaBrutaRec(alg, hash, salt, buf, len + 1);
                if (res != null) return res;
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        long dif = t2 - t1;

        long mil = dif % 1000;
        long totSecs = dif / 1000;
        long secs = totSecs % 60;
        long totMins = totSecs / 60;
        long mins = totMins % 60;
        long totHours = totMins / 60;
        long hours = totHours % 24;
        long days = totHours / 24;

        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, mins, secs, mil);
    }
}