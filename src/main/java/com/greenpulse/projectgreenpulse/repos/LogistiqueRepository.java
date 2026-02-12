package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Logistique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogistiqueRepository extends JpaRepository<Logistique, Long> {
}