public class Rot13 {
    public static final String sus = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
    public static final char[] minuscules = sus.toCharArray();
    public static final char[] majuscules = sus.toUpperCase().toCharArray();
    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("\nXifrat\n---------");
        
        //XIFRA
        for(int i = 0; i < msgs.length; i++) {
            msgsXifrats[i] = xifraRot13(msgs[i]);
            System.out.printf("%-23s => %s%n", msgs[i], msgsXifrats[i]);
        }

        //DESXIFRA
        System.out.println("\nDesxifrat\n---------");
        for(String msg : msgsXifrats) {
            System.out.printf("%-23s => %s%n", msg, desxifraRot13(msg));
        }
    }

    public static String xifraRot13(String text) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);                      //seguir
            
            if(Character.isLetter(car)) {
                if (Character.isUpperCase(car)) {
                    for(int y = 0; y < majuscules.length; y++) {
                        char c = majuscules[y];
                        if(car == c) {
                            res += majuscules[(y+13)%majuscules.length];
                            break;
                        }
                    }
                }
                else {
                    for(int y = 0; y < minuscules.length; y++) {
                        char c = minuscules[y];
                        if(car == c) {
                            res += minuscules[(y+13)%minuscules.length];
                            break;
                        }
                    }
                }
            }
            else res += car;
        }
        
        return res;
    }
    public static String desxifraRot13(String text) {
        String res = "";
        for(int i = 0; i < text.length(); i++) {
            char car = text.charAt(i);
            if(Character.isLetter(car)) {
                if(Character.isUpperCase(car)) {
                    for(int y = 0; y < majuscules.length; y++) {
                        char c = majuscules[y];
                        if(car == c) {
                            res += majuscules[(y+majuscules.length-13)%majuscules.length];
                            break;
                        }
                    }
                }
                else {
                    for(int y = 0; y < minuscules.length; y++) {
                        char c = minuscules[y];
                        if(car == c) {
                            res += minuscules[(y+minuscules.length-13)%minuscules.length];
                            break;
                        }
                    }
                }
            }
            else res += car;
        }
        return res;
    }
}