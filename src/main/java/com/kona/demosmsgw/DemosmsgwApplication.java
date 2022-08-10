package com.kona.demosmsgw;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kona.demosmsgw.stub.HeaderRequest;
import com.kona.demosmsgw.stub.SendSmsRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource(locations = "${camel.context.config.location}")
@EnableCaching
public class DemosmsgwApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemosmsgwApplication.class, args);
	}

}
