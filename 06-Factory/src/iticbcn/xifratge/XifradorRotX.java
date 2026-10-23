package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] minuscules = lletres.toCharArray();
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int num = parseClau(clau);
        return new TextXifrat(xifraRotX(msg, num).getBytes(java.nio.charset.StandardCharsets.UTF_8));
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int num = parseClau(clau);
        return desxifraRotX(xifrat.toString(), num);
    }

    private int parseClau(String clau) throws ClauNoSuportada {
        try {
            int k = Integer.parseInt(clau);
            if (k < 0 || k > 40) throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
            return k;
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    public String xifraRotX(String text, int num) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);
            
            if(Character.isLetter(car)) {
                if (Character.isUpperCase(car)) {
                    for(int y = 0; y < majuscules.length; y++) {
                        char c = majuscules[y];
                        if(car == c) {
                            res += majuscules[(y+num)%majuscules.length];
                            break;
                        }
                    }
                }
                else {
                    for(int y = 0; y < minuscules.length; y++) {
                        char c = minuscules[y];
                        if(car == c) {
                            res += minuscules[(y+num)%minuscules.length];
                            break;
                        }
                    }
                }
            }
            else res += car;
        }
        
        return res;
    }

    public String desxifraRotX(String text, int num) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);
            
            if(Character.isLetter(car)) {
                if (Character.isUpperCase(car)) {
                    for(int y = 0; y < majuscules.length; y++) {
                        char c = majuscules[y];
                        if(car == c) {
                            res += majuscules[(y+majuscules.length-num)%majuscules.length];
                            break;
                        }
                    }
                }
                else {
                    for(int y = 0; y < minuscules.length; y++) {
                        char c = minuscules[y];
                        if(car == c) {
                            res += minuscules[(y+minuscules.length-num)%minuscules.length];
                            break;
                        }
                    }
                }
            }
            else res += car;
        }
        
        return res;
    }
    
    public void forcaBrutaRotX(String text) {
        System.out.printf("\nMissatge xifrat: %s%n-----------------%n", text);
        for(int index = 0 ; index < majuscules.length; index++) {
            String res = "";
            for(int i = 0; i < text.length(); i++) {
                char car = text.charAt(i);
                if(Character.isLetter(car)) {
                    if(Character.isUpperCase(car)) {
                        for(int y = 0; y < majuscules.length; y++) {
                            char car2 = majuscules[y];
                            if(car == car2) res += majuscules[(y+majuscules.length-index)%majuscules.length];
                        }
                    }
                    else {
                        for(int y = 0; y < minuscules.length; y++) {
                            char car2 = minuscules[y];
                            if(car == car2) res += minuscules[(y+majuscules.length-index)%minuscules.length];
                        }
                    }
                } else res += car;
            }
            System.out.printf("(%d)->%s\n", index, res);
        }
    }
}