package kz.asetkenes.solidbankapp.ui;

import kz.asetkenes.solidbankapp.domain.account.entities.AccountType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Getter
@Component
public class MyCli implements CliUi {

    @Autowired
    private final Scanner scanner;

    public MyCli() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public AccountType requestAccountType() {
        String outputMessage = """
                Choose account type:
                [CHECKING, SAVING, FIXED]
                """;
        System.out.println(outputMessage);

        String inputAccountType = scanner.nextLine();
        String inputAccountTypeUpperCase = inputAccountType.toUpperCase();

        return switch (inputAccountTypeUpperCase) {
            case "CHECKING" -> AccountType.CHECKING;
            case "SAVING" -> AccountType.SAVING;
            case "FIXED" -> AccountType.FIXED;
            default -> throw new IllegalArgumentException("Incorrect AccountType");
        };
    }

    @Override
    public double requestClientAmount() {
        boolean isDouble;
        double amount = 0;

        do {
            System.out.print("Input amount: ");
            try {
                amount = Double.parseDouble(scanner.nextLine());
                isDouble = true;
            } catch (NumberFormatException ex) {
                System.out.println("Incorrect amount please try again");
                isDouble = false;
            }
        } while (!isDouble);

        return amount;
    }

    @Override
    public String requestClientAccountNumber() {
        System.out.print("Input account number: ");

        return scanner.nextLine();
    }

}
