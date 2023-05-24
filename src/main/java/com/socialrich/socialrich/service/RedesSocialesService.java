package com.socialrich.socialrich.service;

import com.socialrich.socialrich.dto.RedesSocialesDTO;
import com.socialrich.socialrich.entity.RedesSociales;

import java.util.List;

public interface RedesSocialesService {
    RedesSocialesDTO createRedesSociales(RedesSocialesDTO redesSocialesDTO);

    RedesSocialesDTO getRedesSocialesById(Long redesSocialesId);

    List<RedesSocialesDTO> getAllRedesSociales();

    RedesSocialesDTO updateRedesSociales(RedesSocialesDTO redesSocialesDTO, Long redesSocialesID);

    void deleteRedesSociales(Long redesSocialesId);

    RedesSocialesDTO convertEntitytoDTO(RedesSociales redesSociales);

    RedesSociales convertDTOtoEntity(RedesSocialesDTO redesSocialesDTO);

    List<RedesSocialesDTO> convertListEntitytoListDTO (List<RedesSociales> redesSocialesList);

    List<RedesSociales> convertListDTOtoListEntity (List<RedesSocialesDTO> redesSocialesListDTO);
}


