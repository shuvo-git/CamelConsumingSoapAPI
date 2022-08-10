package com.kona.demosmsgw.service;

import com.kona.demosmsgw.dto.SendSmsRequestDto;
import com.kona.demosmsgw.stub.HeaderRequest;
import com.kona.demosmsgw.stub.HeaderResult;
import com.kona.demosmsgw.stub.SendSMSResponse;
import com.kona.demosmsgw.stub.SendSmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.cxf.message.MessageContentsList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import ltc.srv.com.ltc;

import static org.apache.commons.lang3.StringUtils.trim;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {
    private final String privatekey = "9FvNO/DRAK84onxxRQU1WiDrV+Of2SPfa2jdyBGktdc=";
    private final ProducerTemplate producerTemplate;

    @Override
    public SendSMSResponse sendSoapRequest(SendSmsRequestDto sendSmsRequestDto) {
        List<Object> soapRequestBody = new ArrayList<>();

        HeaderRequest header = new HeaderRequest();

        header.setUserid(sendSmsRequestDto.getUserid());
        header.setTransId(sendSmsRequestDto.getTransId());
        header.setKey(ltc.encrypt(
                trim(sendSmsRequestDto.getUserid())+trim(sendSmsRequestDto.getTransId()),
                privatekey
        ));
        header.setVerson(sendSmsRequestDto.getVersion());

        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setHeader(header);
        sendSmsRequest.setMsisdn(sendSmsRequestDto.getMsisdn());
        sendSmsRequest.setHeaderSMS(sendSmsRequestDto.getHeaderSMS());
        sendSmsRequest.setMessage(sendSmsRequestDto.getMessage());
        sendSmsRequest.setSmsType(sendSmsRequestDto.getSmsType());


        soapRequestBody.add(sendSmsRequest);
        log.info("Initiating SMS Broadcasting procedure...");

        try {
            HeaderResult response = producerTemplate.requestBody(
                    "direct:send-sms",
                    soapRequestBody,
                    HeaderResult.class
            );



            SendSMSResponse buildResponse = new SendSMSResponse();
            buildResponse.setSendSMSResult(response);
            return buildResponse;

        } catch (CamelExecutionException e) {
            SendSMSResponse buildResponse = new SendSMSResponse();
            HeaderResult headerResult = new HeaderResult();

            headerResult.setResultCode("999");
            headerResult.setResultDesc(e.getMessage());

            buildResponse.setSendSMSResult(headerResult);
            return buildResponse;
        } catch (NullPointerException e) {
            SendSMSResponse buildResponse = new SendSMSResponse();
            HeaderResult headerResult = new HeaderResult();

            headerResult.setResultCode("999");
            headerResult.setResultDesc(e.getMessage());

            buildResponse.setSendSMSResult(headerResult);
            return buildResponse;
        }
    }
}
