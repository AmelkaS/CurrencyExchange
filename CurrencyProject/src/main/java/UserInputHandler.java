import java.util.Scanner;

public class UserInputHandler implements IUserInputHandler {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public double getAmount() {
        System.out.print("Enter the amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    @Override
    public String getCurrencyCode(String prompt) {
        System.out.print(prompt);
        return scanner.next().toUpperCase();
    }
}
