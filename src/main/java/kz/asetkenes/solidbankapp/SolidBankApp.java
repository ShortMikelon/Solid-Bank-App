package kz.asetkenes.solidbankapp;

import kz.asetkenes.solidbankapp.cli.AccountBasicCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransactionDepositCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransactionWithdrawCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransferCli;
import kz.asetkenes.solidbankapp.ui.MyCli;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SolidBankApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        String clientId = "1";

        MyCli myCli = context.getBean(MyCli.class);

        AccountBasicCli basicCli = context.getBean(AccountBasicCli.class);

        TransactionWithdrawCli withdrawCli = context.getBean(TransactionWithdrawCli.class);
        TransactionDepositCli depositCli = context.getBean(TransactionDepositCli.class);

        TransferCli transferCli = context.getBean(TransferCli.class);

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
            System.out.print("\nInput number operation: ");
            String inputOperationNumber = myCli.getScanner().nextLine();

            switch (inputOperationNumber) {
                case "1" -> basicCli.getAccounts(clientId);
                case "2" -> basicCli.createAccountRequest(clientId);
                case "3" -> depositCli.depositMoney(clientId);
                case "4" -> withdrawCli.withdrawMoney(clientId);
                case "5" -> transferCli.transfer(clientId);
                case "6" -> System.out.println(helpMessage);
                case "7" -> isFinished = true;
                default -> System.out.println("Incorrect number operation. Please Again!");
            }
        }

        System.out.println("Application closed");
    }
}
