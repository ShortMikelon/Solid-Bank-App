package kz.asetkenes.solidbankapp.ui;

import kz.asetkenes.solidbankapp.domain.entities.AccountType;

import java.util.Scanner;

public class MyCli implements CliUi {

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

        AccountType accountType = switch (inputAccountTypeUpperCase) {
            case "CHECKING" -> AccountType.CHECKING;
            case "SAVING" -> AccountType.SAVING;
            case "FIXED" -> AccountType.FIXED;
            default -> throw new IllegalArgumentException("Incorrect AccountType");
        };

        return accountType;
    }

    public Scanner getScanner() {
        return scanner;
    }
}
