package com.socialrich.socialrich.service;

import com.socialrich.socialrich.entity.RedesSociales;

import java.util.List;

public interface RedesSocialesService {
    RedesSociales createRedesSociales(RedesSociales redesSociales);

    RedesSociales getRedesSocialesById(Long redesSocialesId);

    List<RedesSociales> getAllRedesSociales();

    RedesSociales updateRedesSociales(RedesSociales redesSociales);

    void deleteRedesSociales(Long redesSocialesId);
}


