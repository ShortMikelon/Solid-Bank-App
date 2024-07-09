package kz.asetkenes.solidbankapp;

import kz.asetkenes.solidbankapp.ui.AccountBasicCli;
import kz.asetkenes.solidbankapp.ui.MyCli;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class SolidBankApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("props.xml");
        String clientId = "1";

        AccountBasicCli basicCli = (AccountBasicCli) context.getBean("accountBasicCli");
        MyCli myCli = (MyCli) context.getBean("myCli");

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
                case "1": {
                    basicCli.getAccounts(clientId);
                    break;
                }
                case "2": {
                    basicCli.createAccountRequest(clientId);
                    break;
                }
                case "3": {
                    System.out.println("Not yet implementation");
                    break;
                }
                case "4": {
                    System.out.println("Not yet implementation");
                    break;
                }
                case "5": {
                    System.out.println("Not yet implementation");
                    break;
                }
                case "6": {
                    System.out.println(helpMessage);
                    break;
                }
                case "7": {
                    System.out.println("Application closed");
                    isFinished = true;
                    break;
                }
                default: System.out.println("Incorrect number operation. Please Again!");
            }
        }
    }
}
