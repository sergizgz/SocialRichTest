package com.socialrich.socialrich.controllers;

import com.socialrich.socialrich.constants.Constants;
import com.socialrich.socialrich.dto.RedesSocialesDTO;
import com.socialrich.socialrich.exceptions.NoRedSocialException;
import com.socialrich.socialrich.exceptions.NoUserException;
import com.socialrich.socialrich.service.RedesSocialesService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redessociales")
@Slf4j
public class RedesSocialesController {

    @Autowired
    private RedesSocialesService redesSocialesService;

    @GetMapping("/")
    public ResponseEntity<List<RedesSocialesDTO>> getAllRedesSociales() throws NoRedSocialException {
        List<RedesSocialesDTO> redesSocialesDTO = redesSocialesService.getAllRedesSociales();
        if (redesSocialesDTO.isEmpty()) {
            log.warn(Constants.NO_RED_SOCIAL);
            throw new NoRedSocialException();
        }
        return ResponseEntity.ok(redesSocialesDTO);
    }
    @GetMapping("/{redesSocialesId}")
    public ResponseEntity<RedesSocialesDTO> getRedesSocialesById(@PathVariable Long redesSocialesId) throws NoRedSocialException {
        RedesSocialesDTO redesSocialesDTO = redesSocialesService.getRedesSocialesById(redesSocialesId);
        if (redesSocialesDTO == null) {
            log.warn(Constants.NO_RED_SOCIAL);
            throw new NoRedSocialException();
        }
        return ResponseEntity.ok(redesSocialesDTO);
    }


    @PostMapping("/")
    public ResponseEntity<RedesSocialesDTO> createRedesSociales(@RequestBody RedesSocialesDTO redesSocialesDTO) {
        RedesSocialesDTO savedRedesSocialesDTO = redesSocialesService.createRedesSociales(redesSocialesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRedesSocialesDTO);
    }

    @PutMapping("/{redesSocialesId}")
    public ResponseEntity<RedesSocialesDTO> updateRedesSociales(@PathVariable Long redesSocialesId, @RequestBody RedesSocialesDTO redesSocialesDTO) throws NoRedSocialException {

        RedesSocialesDTO updatedRedesSocialesDTO = redesSocialesService.updateRedesSociales(redesSocialesDTO, redesSocialesId);
        if (updatedRedesSocialesDTO== null) {
            log.warn(Constants.NO_RED_SOCIAL);
            throw new NoRedSocialException();
        }
        return ResponseEntity.ok(updatedRedesSocialesDTO);
    }

    @DeleteMapping("/{redesSocialesId}")
    public ResponseEntity<Void> deleteRedesSociales(@PathVariable Long redesSocialesId) {
        redesSocialesService.deleteRedesSociales(redesSocialesId);
        return ResponseEntity.noContent().build();
    }






}
