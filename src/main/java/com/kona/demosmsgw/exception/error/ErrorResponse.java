package com.kona.demosmsgw.exception.error;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorResponse
{
    private String message;
    private int status;
    private List<ErrorModel> errorMessages;
}
