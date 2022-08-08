package com.kona.demosmsgw.service;

import com.kona.demosmsgw.dto.SendSmsRequestDto;
import com.kona.demosmsgw.stub.SendSMSResponse;
import com.kona.demosmsgw.stub.SendSmsRequest;


public interface SmsService {
    public SendSMSResponse sendSoapRequest(SendSmsRequestDto sendSmsRequest);
}
