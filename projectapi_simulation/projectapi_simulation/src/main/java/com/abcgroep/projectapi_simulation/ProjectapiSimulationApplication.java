package com.abcgroep.projectapi_simulation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ProjectapiSimulationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectapiSimulationApplication.class, args);
    }

}
