package com.falsefalse.where2.utils;

import com.falsefalse.where2.persistence.entities.EventEntity;
import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import com.falsefalse.where2.persistence.entities.enums.Role;
import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Component
public class DBSeed implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EventRepository eventRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(UserEntity.builder()
                .displayName("Bartul Gasperov")
                .username("bgasperov")
                .password("my_Strong_Password")
                .email("bg46685@unist.hr")
                .role(Role.ADMIN)
                .build());

        userRepository.save(UserEntity.builder()
                .displayName("Josip Marinovic")
                .username("ljigavac96")
                .password("sveucilisnaKnjiznica")
                .email("jm342342@unist.hr")
                .role(Role.USER)
                .build());

        var marioMileni = UserEntity.builder()
                .displayName("Mario Mileni")
                .username("milethicc")
                .password("zlopolje1950")
                .email("mm342328@unist.hr")
                .role(Role.USER)
                .build();
        userRepository.save(marioMileni);

        eventRepository.save(
                EventEntity.builder()
                .name("Vecer piletine")
                .eventType(EventType.SPECIAL_PROMOTION)
                .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                .subscribedUsers(List.of(marioMileni))
                .price(0)
                .currency(Currency.EUR)
                .isRecurring(false)
                .build());
    }
}
