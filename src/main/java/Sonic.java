import java.util.Scanner;

public class Sonic {
    public static void main(String[] args) {
        String logo = " SSSSS  OOOOO  N   N  III  CCCCC \n"
                + " S      O   O  NN  N   I   C     \n"
                + "  SSS   O   O  N N N   I   C     \n"
                + "     S  O   O  N  NN   I   C     \n"
                + " SSSSS  OOOOO  N   N  III  CCCCC \n";

        System.out.println("Hello from\n" + logo);
        String line = "__________________________________________________________";
        System.out.println("I am Sonic! What can I do for you?");
        System.out.println(line);

        Scanner userInputScanner = new Scanner(System.in);

        while (true) {
            String userInput = userInputScanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }
            System.out.println(userInput);
            System.out.println(line);
        }


    }
}
