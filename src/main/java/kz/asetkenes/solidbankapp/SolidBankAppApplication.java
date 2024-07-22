package kz.asetkenes.solidbankapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SolidBankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolidBankAppApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		SolidBankApp bankApp = context.getBean(SolidBankApp.class);

		bankApp.startCli();
	}

}
