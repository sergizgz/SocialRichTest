package com.socialrich.socialrich.repository;

import com.socialrich.socialrich.entity.RedesSociales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedesSocialesRepository extends JpaRepository<RedesSociales, Long> {

}
