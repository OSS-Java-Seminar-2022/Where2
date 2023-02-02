package com.falsefalse.where2.utils;

import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeed implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(UserEntity.builder()
                .displayName("Josip Marinovic")
                .username("ljigavac96")
                .password("sveucilisnaKnjiznica")
                .build());

        userRepository.save(UserEntity.builder()
                .displayName("Mario Mileni")
                .username("milethicc")
                .password("zlopolje1950")
                .build());
    }
}
