package com.kona.demosmsgw.eip;

import com.kona.demosmsgw.stub.HeaderResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SoapSmsSenderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:send-sms")
                .doTry()

            .setHeader("operationName",simple("sendSMS"))
            .to("sendSmsEndpoint")
                .doCatch(Exception.class)
            .process(exchange -> {
                log.debug("Entry into Process........");

                 if (exchange.getProperty("CamelExceptionCaught") != null) {
                     Exception ex = (Exception) exchange.getProperty("CamelExceptionCaught");
                     log.error("EX: "+ex.getMessage());


                 } else {
                     HeaderResult headerResult = (HeaderResult) exchange.getIn().getBody();
                     log.info("[SUCCESS:] {}", headerResult);

                     exchange.getIn().setBody(headerResult);
                 }

            });
    }
}
