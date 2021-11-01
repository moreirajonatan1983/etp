package org.utn.frd.dds.etp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class MainApp {

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);
    }

}