package com.socialrich.socialrich.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.io.Serializable;

@Data
public class RedesSocialesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String url;
}
