import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Polialfabetic {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();
    public static final char permutat[] = new char[majuscules.length];

    public static final long clauSecreta = 123;
    public static Random rand;

    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre", 
            "Test 02 Taüll, DÍA, año", 
            "Test 03 Peça, Òrrius, Bòvila"
        };
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for(int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for(int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static String xifraPoliAlfa(String msg) {
        String res = "";

        for(int i = 0; i < msg.length(); i++) {
            permutaAlfabet();
            char car = msg.charAt(i);
            if(obteIndex(Character.toUpperCase(car), majuscules) > -1) {
                int ind = obteIndex(Character.toUpperCase(car), majuscules);
                if(Character.isUpperCase(car)) res += permutat[ind];
                else res += Character.toLowerCase(permutat[ind]);
            } else res += car;
        }

        return res;
    }
    
    public static String desxifraPoliAlfa(String msgXifrat) {
        String res = "";
        for(int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            char car = msgXifrat.charAt(i);
            if(obteIndex(Character.toUpperCase(car), majuscules) > -1) {
                int ind = obteIndex(Character.toUpperCase(car), permutat);
                if(Character.isUpperCase(car)) res += majuscules[ind];
                else res += Character.toLowerCase(majuscules[ind]);
            } else res += car;
        }

        return res;
    }

    
    public static void permutaAlfabet() {
        ArrayList<Character> list = new ArrayList<>();

        for(char c : majuscules) {
            list.add(c);
        }

        Collections.shuffle(list, rand);

        char res[] = new char[list.size()];
        for(int i = 0; i < list.size(); i++) {
            permutat[i] = list.get(i);
        }

    }

    public static int obteIndex(char c, char[] list) {
        for(int i = 0; i < list.length; i++) {
            char car = list[i];
            if (c == car) return i;
        }
        
        return -1;
    }

    public static void initRandom(long clau) { rand = new Random(clau); }
}