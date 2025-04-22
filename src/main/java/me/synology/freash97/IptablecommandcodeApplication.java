package me.synology.freash97;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class IptablecommandcodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(IptablecommandcodeApplication.class, args);
    }

}
