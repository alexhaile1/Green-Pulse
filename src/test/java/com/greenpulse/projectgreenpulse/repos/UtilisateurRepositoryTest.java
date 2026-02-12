package com.greenpulse.projectgreenpulse.repos;

import com.greenpulse.projectgreenpulse.entities.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testCreateUtilisateur() {/*
        Utilisateur userKarime = new Utilisateur("Alain", "Karim","karim@gmail.com", "password123");
        userKarime.ajouter(roleAdmin);
        Utilisateur savedUser =  repos.save(userKarime);
        assertThat(savedUser.getId()).isGreaterThan(0);*/
    }
}
