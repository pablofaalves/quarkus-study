package br.com.pfaa.quarkusstudy.application.handler.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionHandlerResponseDto {
    private String message;
}
