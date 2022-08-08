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
		SendSmsRequest request = new SendSmsRequest();
		HeaderRequest headerRequest = new HeaderRequest();
		headerRequest.setKey("key");
		headerRequest.setUserid("uid");
		headerRequest.setTransId("TR*****000");
		headerRequest.setVerson("v");
		request.setHeader(headerRequest);
		request.setMsisdn("236782387");
		request.setMessage("Message");
		request.setSmsType("66");
		request.setHeaderSMS("hedSMS");

		System.out.println(request);

		ObjectMapper Obj = new ObjectMapper();

		// Try block to check for exceptions
		try {

			// Getting organisation object as a json string
			String jsonStr = Obj.writeValueAsString(request);

			// Displaying JSON String on console
			System.out.println(jsonStr);
		}
		catch (JsonProcessingException e){
			e.printStackTrace();
		}


		SpringApplication.run(DemosmsgwApplication.class, args);
	}

}
