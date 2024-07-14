package kz.asetkenes.solidbankapp;

import kz.asetkenes.solidbankapp.ui.AccountBasicCli;
import kz.asetkenes.solidbankapp.ui.MyCli;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SolidBankApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String clientId = "1";

        AccountBasicCli basicCli = context.getBean(AccountBasicCli.class);
        MyCli myCli = context.getBean(MyCli.class);

        String helpMessage = """
				1 - show accounts
				2 - create account
				3 - deposit
				4 - withdraw
				5 - transfer
				6 - this message
				7 - exit
				""";
        boolean isFinished = false;

        System.out.println(helpMessage);
        while (!isFinished) {
            System.out.print("Input number operation: ");
            String inputOperationNumber = myCli.getScanner().nextLine();

            switch (inputOperationNumber) {
                case "1" -> basicCli.getAccounts(clientId);
                case "2" -> basicCli.createAccountRequest(clientId);
                case "3" -> System.out.println("Not yet implementation");
                case "4" -> System.out.println("Not yet implementation");
                case "5" -> System.out.println("Not yet implementation");
                case "6" -> System.out.println(helpMessage);
                case "7" -> {
                    System.out.println("Application closed");
                    isFinished = true;
                }
                default -> System.out.println("Incorrect number operation. Please Again!");
            }
        }
    }
}
