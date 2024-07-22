package kz.asetkenes.solidbankapp;

import kz.asetkenes.solidbankapp.cli.account.AccountBasicCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransactionDepositCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransactionWithdrawCli;
import kz.asetkenes.solidbankapp.cli.transaction.TransferCli;
import kz.asetkenes.solidbankapp.ui.MyCli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SolidBankApp {

    private final AccountBasicCli basicCli;

    private final MyCli myCli;

    private final TransactionWithdrawCli withdrawCli;

    private final TransactionDepositCli depositCli;

    private final TransferCli transferCli;

    @Autowired
    public SolidBankApp(
            AccountBasicCli basicCli,
            MyCli myCli,
            TransactionWithdrawCli withdrawCli,
            TransactionDepositCli depositCli,
            TransferCli transferCli
    ) {
        this.basicCli = basicCli;
        this.myCli = myCli;
        this.withdrawCli = withdrawCli;
        this.depositCli = depositCli;
        this.transferCli = transferCli;
    }

    public void startCli() {
        String clientId = "1";

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
            String inputOperationNumber = this.myCli.getScanner().nextLine();

            switch (inputOperationNumber) {
                case "1" -> this.basicCli.getAccounts(clientId);
                case "2" -> this.basicCli.createAccountRequest(clientId);
                case "3" -> this.depositCli.depositMoney(clientId);
                case "4" -> this.withdrawCli.withdrawMoney(clientId);
                case "5" -> this.transferCli.transfer(clientId);
                case "6" -> System.out.println(helpMessage);
                case "7" -> isFinished = true;
                default -> System.out.println("Incorrect number operation. Please Again!");
            }
        }

        System.out.println("Application closed");
    }
}
