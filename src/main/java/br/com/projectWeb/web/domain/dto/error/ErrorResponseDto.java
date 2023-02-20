package br.com.projectWeb.web.domain.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponseDto {
    private String massage;
    private HttpStatus httpStatus;
    private Integer statusCode;
}