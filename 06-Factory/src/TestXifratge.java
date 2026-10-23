import iticbcn.xifratge.*;

public class TestXifratge {
    public static void main(String[] args) {
        AlgorismeFactory[] aFactory = {
            new AlgorismeAES(), new AlgorismeMonoalfabetic(),
            new AlgorismePolialfabetic(), new AlgorismeRotX()
        };

        String[] noms = { "AES", "Monoalfabètic", "Polialfabètic", "RotX" };

        String[] msgs = { "Test 01: Àlgid, Ülrich, Vàlid",
                          "Test 02: Caràcters especials ¡!¿?*-123[]{}@#" };

        String[][] keys = { { "Clau Secreta 01" }, { "ErrorClau", null },
                             { "ErrorClau2", "123456" }, { "-1", "13", "1000", "Errorclau" } };

        for (int i = 0; i < aFactory.length; i++) {
            AlgorismeFactory alg = aFactory[i];
            String nom = noms[i];

            Xifrador xifrador = alg.creaXifrador();

            System.out.println("=================");
            
            for (String msg : msgs) {
                for (String key : keys[i]) {
                    TextXifrat txt = null;
                    String desxifrat = null;

                    System.out.printf("Missatge: %s\n", msg);
                    System.out.printf("Algorisme: %s\n", nom);
                    System.out.printf("Clau: %s\n", key);

                    try {
                        txt = xifrador.xifra(msg, key);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.println("TextXifrat: " + txt);


                    try {
                        desxifrat = xifrador.desxifra(txt, key);
                    } catch (ClauNoSuportada e) {
                        System.err.println(e.getLocalizedMessage());
                    }
                    System.out.printf("Desxifrat: %s\n", desxifrat);
                    System.out.println("-------------");
                }
            }
        }
    }
}