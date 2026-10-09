import java.util.ArrayList;
import java.util.Collections;
public class XifradorMonoalfabetic {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();
    public static final char permutat[] = permutaAlfabet(majuscules);

    public static void main(String[] args) {
        String proves[] = {"Test 01 àrbitre, coixí, Perímetre", "Test 02 Taüll, DÍA, año", "Test 03 Peça, Òrrius, Bòvila"};
    
        for(char c : majuscules) {
            System.out.printf("%c ", c);
        }
        System.out.println();

        for(char c : permutat) {
            System.out.printf("%c ", c);
        }

        System.out.println();
        //XIFRA
        System.out.printf("\nXifratge:\n--------------\n");
        String xifrat[] = new String[proves.length];
        for(int i = 0; i < proves.length; i++) {
            String text = proves[i];
            xifrat[i] = xifraMonoAlfa(text);
            System.out.printf("%-23s => %s\n", text, xifraMonoAlfa(text));
        }

        System.out.println();
        //DESXIFRA
        System.out.printf("Desxifratge:\n--------------\n");
        for(String text : xifrat) {
            System.out.printf("%-23s => %s\n", text, desxifraMonoAlfa(text));
        }

    }

    public static String xifraMonoAlfa(String text) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);
            if(Character.isLetter(car)) {
                if(Character.isUpperCase(car)) {
                    res += permutat[buscaIndex(car, majuscules)];
                }
                else {
                    res += Character.toLowerCase(permutat[buscaIndex(Character.toUpperCase(car), majuscules)]);
                }
            } else res += car;
        }
        return res;
    }

    public static String desxifraMonoAlfa(String text) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);
            if(Character.isLetter(car)) {
                if(Character.isUpperCase(car)) {
                    res += majuscules[buscaIndex(car, permutat)];
                }
                else {
                    res += Character.toLowerCase(majuscules[buscaIndex(Character.toUpperCase(car), permutat)]);
                }
            } else res += car;
        }
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

    public static int buscaIndex(char car, char[] list) {
        for(int i = 0; i < list.length; i++) {
            char c = list[i];
            if(car == c) return i;
        }
        
        return -1;
    }
}