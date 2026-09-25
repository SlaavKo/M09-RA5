import java.util.ArrayList;
import java.util.Collections;
public class Monoalfabetic {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();
    char permutat[] = permutaAlfabet(majuscules);

    public static void main(String[] args) {
        String proves[] = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
    
        
        //XIFRA
        System.out.printf("Xifratge:\n--------------\n");
        String xifrat[] = new String[proves.length];
        for(int i = 0; i < proves.length; i++) {
            String text = proves[i];
            xifrat[i] = xifraMonoAlfa(text);
            System.out.printf("%-23s => %s\n", text, xifraMonoAlfa(text));
        }

        //DESXIFRA
        System.out.printf("Desxifratge:\n--------------\n");
        for(String text : xifrat) {
            System.out.printf("%-23s => %s\n", text, desxifraMonoAlfa(text));
        }

    }

    public static String xifraMonoAlfa(String text) {
        String res = "";
        
        return res;
    }

    public static String desxifraMonoAlfa(String text) {
        String res = "";
        
        return res;
    }

    public static char[] permutaAlfabet(char[] lletres) {
        ArrayList<Character> list = new ArrayList<>();

        for(char c : lletres) {
            list.add(c);
        }

        Collections.shuffle(list);

        char res[] = new char[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }
}