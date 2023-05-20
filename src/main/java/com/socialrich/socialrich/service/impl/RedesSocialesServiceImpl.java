package com.socialrich.socialrich.service.impl;

import com.socialrich.socialrich.entity.RedesSociales;
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

        if (optionalRedesSociales.isPresent()){
            return optionalRedesSociales.get();
        }else{
            return null;
        }


    }

    @Override
    public List<RedesSociales> getAllRedesSociales() {
        return redesSocialesRepository.findAll();
    }

    @Override
    public RedesSociales updateRedesSociales(RedesSociales redesSociales) {
        Optional<RedesSociales> optionalRedesSociales = redesSocialesRepository.findById(redesSociales.getId());
            if (optionalRedesSociales.isPresent()) {
                RedesSociales existingRedSocial = optionalRedesSociales.get();
                existingRedSocial.setName(redesSociales.getName());
                existingRedSocial.setUrl(redesSociales.getUrl());
                return redesSocialesRepository.save(existingRedSocial);
            }
        return null;
    }

    @Override
    public void deleteRedesSociales(Long redesSocialesId) {
        redesSocialesRepository.deleteById(redesSocialesId);
    }
}
