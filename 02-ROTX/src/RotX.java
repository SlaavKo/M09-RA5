public class RotX {
    public static final String sus = "aáàbccdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";
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
    public static String desxifraRot13(String text) {
        return "";
    }
    public static String xifraRot13(String text) {
        return "";
    }
}
