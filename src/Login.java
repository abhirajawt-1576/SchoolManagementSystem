import java.util.Scanner;

public class Login {

    static Scanner sc = new Scanner(System.in);

    public static boolean showLogin() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("     SCHOOL MANAGEMENT SYSTEM");
        System.out.println("=================================");

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return LoginDAO.login(username, password);
    }
}