package com.socialrich.socialrich.service.impl;

import com.socialrich.socialrich.dto.RedesSocialesDTO;
import com.socialrich.socialrich.entity.RedesSociales;
import com.socialrich.socialrich.repository.RedesSocialesRepository;
import com.socialrich.socialrich.service.RedesSocialesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RedesSocialesServiceImpl implements RedesSocialesService {


    @Autowired
    private RedesSocialesRepository redesSocialesRepository;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public RedesSocialesDTO createRedesSociales(RedesSocialesDTO redesSocialesDTO) {

        RedesSociales redesSociales = redesSocialesRepository.
                save(convertDTOtoEntity(redesSocialesDTO));

        return convertEntitytoDTO(redesSociales);
    }

    @Override
    public RedesSocialesDTO getRedesSocialesById(Long redesSocialesId) {
        Optional<RedesSociales> optionalRedesSociales = redesSocialesRepository.findById(redesSocialesId);

        if (optionalRedesSociales.isPresent()){

            return convertEntitytoDTO(optionalRedesSociales.get());
        }else{
            return null;
        }


    }

    @Override
    public List<RedesSocialesDTO> getAllRedesSociales() {
        List<RedesSociales> redesSociales = redesSocialesRepository.findAll();

        return convertListEntitytoListDTO(redesSociales);
    }

    @Override
    public RedesSocialesDTO updateRedesSociales(RedesSocialesDTO redesSocialesDTO, Long redesSocialesID) {
        RedesSociales redesSociales = convertDTOtoEntity(redesSocialesDTO);
        redesSociales.setId(redesSocialesID);

        Optional<RedesSociales> optionalRedesSociales = redesSocialesRepository.findById(redesSociales.getId());

            if (optionalRedesSociales.isPresent()) {
                RedesSociales existingRedSocial = optionalRedesSociales.get();
                existingRedSocial.setName(redesSocialesDTO.getName());
                existingRedSocial.setUrl(redesSocialesDTO.getUrl());
                RedesSociales redesSociales2 = redesSocialesRepository.save(existingRedSocial);
                return convertEntitytoDTO(redesSociales2);
            }
        return null;
    }

    @Override
    public void deleteRedesSociales(Long redesSocialesId) {

        redesSocialesRepository.deleteById(redesSocialesId);
    }

    @Override
    public RedesSocialesDTO convertEntitytoDTO(RedesSociales redesSociales) {
        return modelMapper.map(redesSociales,RedesSocialesDTO.class);
    }

    @Override
    public RedesSociales convertDTOtoEntity(RedesSocialesDTO redesSocialesDTO) {
        return modelMapper.map(redesSocialesDTO,RedesSociales.class);
    }

    @Override
    public List<RedesSocialesDTO> convertListEntitytoListDTO(List<RedesSociales> redesSocialesList) {
        return redesSocialesList
                .stream()
                .map(redesSocial -> modelMapper.map(redesSocial, RedesSocialesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RedesSociales> convertListDTOtoListEntity(List<RedesSocialesDTO> redesSocialesListDTO) {
        return redesSocialesListDTO
                .stream()
                .map(redesSocial -> modelMapper.map(redesSocialesListDTO, RedesSociales.class))
                .collect(Collectors.toList());
    }
}
