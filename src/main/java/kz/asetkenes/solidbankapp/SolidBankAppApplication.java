package kz.asetkenes.solidbankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SolidBankAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SolidBankAppApplication.class, args);

		SolidBankApp bankApp = context.getBean(SolidBankApp.class);

		bankApp.startCli();
	}

}
