package kz.asetkenes.solidbankapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
@ComponentScan(basePackages = "kz.asetkenes.solidbankapp")
public class AppConfig {

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
