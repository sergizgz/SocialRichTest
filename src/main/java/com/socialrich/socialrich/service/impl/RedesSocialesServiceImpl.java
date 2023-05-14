package com.socialrich.socialrich.service.impl;

import com.socialrich.socialrich.entity.RedesSociales;
import com.socialrich.socialrich.entity.User;
import com.socialrich.socialrich.repository.RedesSocialesRepository;
import com.socialrich.socialrich.service.RedesSocialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedesSocialesServiceImpl implements RedesSocialesService {


    @Autowired
    private RedesSocialesRepository redesSocialesRepository;
    @Override
    public RedesSociales createRedesSociales(RedesSociales redesSociales) {
        return redesSocialesRepository.save(redesSociales);
    }

    @Override
    public RedesSociales getRedesSocialesById(Long redesSocialesId) {
        Optional<RedesSociales> optionalRedesSociales = redesSocialesRepository.findById(redesSocialesId);
        return optionalRedesSociales.get();
    }

    @Override
    public List<RedesSociales> getAllRedesSociales() {
        return null;
    }

    @Override
    public RedesSociales updateRedesSociales(RedesSociales redesSociales) {
        return null;
    }

    @Override
    public void deleteRedesSociales(Long redesSocialesId) {

    }
}
