package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
public class XifradorMonoalfabetic implements Xifrador {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();
    public char permutat[];


    public XifradorMonoalfabetic() {
        permutat = permutaAlfabet(majuscules);
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        return new TextXifrat(xifraMonoAlfa(msg).getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        return desxifraMonoAlfa(xifrat.toString());
    }

    public String xifraMonoAlfa(String text) {
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

    public String desxifraMonoAlfa(String text) {
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

    public char[] permutaAlfabet(char[] lletres) {
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

    public int buscaIndex(char car, char[] list) { 
        for(int i = 0; i < list.length; i++) {
            char c = list[i];
            if(car == c) return i;
        }
        
        return -1;
    }
}