public class RotX {
    public static final String lletres = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] minuscules = lletres.toCharArray();
    public static final char[] majuscules = lletres.toUpperCase().toCharArray();
    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("\nXifrat\n---------");
        
        //XIFRA
        int index = 0;
        for(int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRotX(msgs[i], index);
            System.out.printf("(%d)%-23s => %s\n", index, msgs[i], msgsXifrats[i]);
            index += 2;
        }

        //DESXIFRA
        System.out.println("\nDesxifrat\n---------");
        index = 0;
        for(String msg : msgsXifrats) {
            System.out.printf("(%d)%-23s => %s\n", index, msg, desxifraRotX(msg, index));
            index += 2;
        }

        //FORÇA BRUTA
        String missatge = msgsXifrats[3];
        forcaBrutaRotX(missatge);
    
    }

    public static String xifraRotX(String text, int num) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);                      //seguir
            
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

    public static String desxifraRotX(String text, int num) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);                      //seguir
            
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
    
    public static void forcaBrutaRotX(String text) {
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