package com.socialrich.socialrich.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDTO {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;


}
