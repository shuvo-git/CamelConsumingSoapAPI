package com.kona.demosmsgw.endpoint;

import com.kona.demosmsgw.dto.SendSmsRequestDto;
import com.kona.demosmsgw.service.SmsService;
import com.kona.demosmsgw.stub.SendSMSResponse;
import com.kona.demosmsgw.stub.SendSmsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SmsController
{
    private final SmsService smsService;

    @PostMapping("/send-sms")
    public SendSMSResponse sendSms(@RequestBody SendSmsRequestDto sendSmsRequest){
        return smsService.sendSoapRequest(sendSmsRequest);
    }


}
