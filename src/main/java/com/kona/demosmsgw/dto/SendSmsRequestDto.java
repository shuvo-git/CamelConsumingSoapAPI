package com.kona.demosmsgw.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SendSmsRequestDto {
    protected String userid;
    protected String transId;
    protected String version;

    protected String msisdn;
    protected String headerSMS;
    protected String message;
    protected String smsType;
}
