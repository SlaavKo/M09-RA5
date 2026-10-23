package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    public final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public final char[] majuscules = lletres.toUpperCase().toCharArray();
    public char permutat[] = new char[majuscules.length];

    public Random rand;

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        return new TextXifrat(xifraPoliAlfa(msg).getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            initRandom(Long.parseLong(clau));
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de Polialfabètic ha de ser un String convertible a long");
        }
        return desxifraPoliAlfa(xifrat.toString());
    }

    public String xifraPoliAlfa(String msg) {
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
    
    public String desxifraPoliAlfa(String msgXifrat) {
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

    
    public void permutaAlfabet() {
        ArrayList<Character> list = new ArrayList<>();

        for(char c : majuscules) {
            list.add(c);
        }

        Collections.shuffle(list, rand);

        for(int i = 0; i < list.size(); i++) {
            permutat[i] = list.get(i);
        }

    }

    public int obteIndex(char c, char[] list) {
        for(int i = 0; i < list.length; i++) {
            char car = list[i];
            if (c == car) return i;
        }
        
        return -1;
    }

    public void initRandom(long clau) { rand = new Random(clau); }
}