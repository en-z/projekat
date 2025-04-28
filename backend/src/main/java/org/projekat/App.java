package org.projekat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class App 
{
    //TODO(EN):dodaj async conf i ostalo za service!!!!! LOGIN I CRYPTO TREBA DA JE SYNC SAMO NOVI THREAD NEKA PRAVI SPRINGBOOT
    public static void main(String[]args){
        System.out.println("JOS NIJE DODAT ASYNC");
        SpringApplication.run(App.class,args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
