package com.socialrich.socialrich.controllers;

import com.socialrich.socialrich.entity.RedesSociales;
import com.socialrich.socialrich.service.RedesSocialesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/redessociales")
public class RedesSocialesController {

    @Autowired
    private RedesSocialesService redesSocialesService;

    @GetMapping("/")
    public ResponseEntity<List<RedesSociales>> getAllRedesSociales() {
        List<RedesSociales> redesSociales = redesSocialesService.getAllRedesSociales();
        if (redesSociales.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(redesSociales);
    }
    @GetMapping("/{redesSocialesId}")
    public ResponseEntity<RedesSociales> getRedesSocialesById(@PathVariable Long redesSocialesId) {
        RedesSociales redesSociales = redesSocialesService.getRedesSocialesById(redesSocialesId);
        if (redesSociales == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(redesSociales);
    }






    @PostMapping("/")
    public ResponseEntity<RedesSociales> createRedesSociales(@RequestBody RedesSociales redesSociales) {
        RedesSociales savedRedesSociales = redesSocialesService.createRedesSociales(redesSociales);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRedesSociales);
    }

    @PutMapping("/{redesSocialesId}")
    public ResponseEntity<RedesSociales> updateRedesSociales(@PathVariable Long redesSocialesId, @RequestBody RedesSociales redesSociales) {
        redesSociales.setId(redesSocialesId);
        RedesSociales updatedRedesSociales = redesSocialesService.updateRedesSociales(redesSociales);
        if (updatedRedesSociales== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedRedesSociales);
    }

    @DeleteMapping("/{redesSocialesId}")
    public ResponseEntity<Void> deleteRedesSociales(@PathVariable Long redesSocialesId) {
        redesSocialesService.deleteRedesSociales(redesSocialesId);
        return ResponseEntity.noContent().build();
    }






}
