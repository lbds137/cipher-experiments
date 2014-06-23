import java.util.Scanner;

public class CipherRunner {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String chooseAction = "";
        String filePath = "";

        System.out
                .println("Would you like to encode or decode? (type \"encode\" or \"decode\")");
        chooseAction = sc.nextLine();
        chooseAction = chooseAction.toLowerCase();

        System.out.println("Type in or copy and paste text to be "
                + chooseAction
                + "d. (or you can type in a file location for decoding)");
        filePath = sc.nextLine();

        Cipher crypt = new Cipher(filePath);
        if (chooseAction.equals("encode")) {
            crypt.encrypt();
            crypt.printResults();
        } else if (chooseAction.equals("decode")) {
            crypt.decrypt();
            crypt.printResults();
        } else {
            System.out
                    .println("Nothing happens because you didn't say the magic word.");
        }
        System.out.println("\nProgram terminated.");
    }

}
