package com.socialrich.socialrich.dto;

import com.socialrich.socialrich.entity.RedesSociales;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String surname;
    private RedesSociales redSocialFavorita;
    private List<RedesSociales> redesSociales;

}
