package com.kona.demosmsgw.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
public class SendSmsRequestDto {


    @Size(min = 4,max = 10)
    @NotNull
    @NotEmpty
    protected String userid;

    @Size(min = 4,max = 20)
    @NotNull
    @NotEmpty
    protected String transId;

    @Size(max = 5)
    protected String version;

    @Size(min = 10,max = 15)
    @NotNull
    @NotEmpty
    protected String msisdn;

    @Size(max = 10)
    @NotNull
    @NotEmpty
    protected String headerSMS;

    @Size(min=10, max = 320)
    @NotNull
    @NotEmpty
    protected String message;

    @Size(min=1, max = 1)
    @NotNull
    @NotEmpty
    protected String smsType;
}
