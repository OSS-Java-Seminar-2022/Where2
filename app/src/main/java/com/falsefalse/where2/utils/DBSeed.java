package com.falsefalse.where2.utils;

import com.falsefalse.where2.persistence.entities.EventEntity;
import com.falsefalse.where2.persistence.entities.UserEntity;
import com.falsefalse.where2.persistence.entities.VenueEntity;
import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import com.falsefalse.where2.persistence.entities.enums.Role;
import com.falsefalse.where2.persistence.entities.enums.VenueType;
import com.falsefalse.where2.persistence.repositories.EventRepository;
import com.falsefalse.where2.persistence.repositories.UserRepository;
import com.falsefalse.where2.persistence.repositories.VenueRepository;
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
    @Autowired
    VenueRepository venueRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        var bg = userRepository.save(UserEntity.builder()
                .displayName("Bartul Gasperov")
                .username("bgasperov")
                .password(passwordEncoder.encode("bg"))
                .email("bg46685@unist.hr")
                .roles(List.of(Role.ADMIN))
                .build());

        var jm = userRepository.save(UserEntity.builder()
                .displayName("Josip Marinovic")
                .username("jmarinovic")
                .password(passwordEncoder.encode("jm"))
                .email("jm342342@unist.hr")
                .roles(List.of(Role.USER))
                .build());

        var marioMileni = userRepository.save(UserEntity.builder()
                .displayName("Mario Mileni")
                .username("milethicc")
                .password(passwordEncoder.encode("zlopolje1950"))
                .email("mm342328@unist.hr")
                .roles(List.of(Role.USER))
                .build());

        var kocka = venueRepository.save(VenueEntity.builder()
                .name("Klub Kocka")
                .venueType(VenueType.CLUB)
                .workingHours("22:00 - 04:00")
                .owner(bg)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRiRnCDxINitB3CTuu0cCJNl4d8udwskfVKdt3xwY3kpndkcUqaVWzOgtjGNrWySKTNpVg&usqp=CAU")
                .build());

        var kvazi = venueRepository.save(VenueEntity.builder()
                .name("Klub Quasimodo")
                .venueType(VenueType.CLUB)
                .workingHours("23:00 - 04:00")
                .owner(jm)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5_sgueOs0yuz73F-nMaBBh3G4CE-1sLQpZhkyT-VP6WZHZ-UtxB5VIhYeBxJ3WqXtwmg&usqp=CAU")
                .build());

        var kfc = venueRepository.save(VenueEntity.builder()
                .name("KFC - Mall of Split")
                .venueType(VenueType.RESTAURANT)
                .workingHours("09:00 - 22:00")
                .owner(marioMileni)
                .imageUrl("https://play-lh.googleusercontent.com/QCzljwz8YHBIno8Cve6bkbMtZZ8YABCB4cqF4kK13ZcgEVxLeoi9AvfVPNk5dp-P7kI")
                .build());

        var vanila = venueRepository.save(VenueEntity.builder()
                .name("Vanilla Club Split")
                .venueType(VenueType.CLUB)
                .workingHours("23:00 - 04:00")
                .owner(jm)
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAMhAEADM8UJ4atQbUpFwzgclxG1d4jt801s4nJU8-yI_Np5SQ3qtjL1dPdkLXt9_m0SI&usqp=CAU")
                .build());

        var bace = venueRepository.save(VenueEntity.builder()
                .name("Klub Bačvice")
                .venueType(VenueType.CLUB)
                .workingHours("23:00 - 04:00")
                .owner(jm)
                .imageUrl("https://cdn.holiday-link.com/images/article/caffe-club-bacvice-281-split_161024247819.jpg")
                .build());

        var asc = venueRepository.save(VenueEntity.builder()
                .name("Adriatic Social Club")
                .venueType(VenueType.BAR)
                .workingHours("09:00 - 00:00")
                .owner(jm)
                .imageUrl("https://i1.sndcdn.com/avatars-000629758545-ydwo9f-t500x500.jpg")
                .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("Otvorenje ljetne sezone // Dalmatino")
                        .eventType(EventType.LIVE_MUSIC)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://scontent-vie1-1.xx.fbcdn.net/v/t1.6435-9/204414749_4381329168578087_1633722051690054837_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=340051&_nc_ohc=cuP2r3Z4iYUAX9WbNwh&_nc_ht=scontent-vie1-1.xx&oh=00_AfDLwO4DOgSxFATbrJarA5vQ2nCEAs9znU9PXGUPIlXH9g&oe=640D8D3B")
                        .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                        .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                        .createdBy(bg)
                        .users(List.of(marioMileni))
                        .price(0)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(vanila)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("AKCIJA Vodka & 4x RedBull")
                        .eventType(EventType.SPECIAL_PROMOTION)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRucZJtLGbRtEWLYTOffGgHYv6r1BE2ghMw32QVpk-Q9eHDN3hrBLI0rnlb48gAMBgl9YA&usqp=CAU")
                        .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                        .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                        .createdBy(bg)
                        .users(List.of(marioMileni))
                        .price(0)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(bace)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("70's Disco Saturday")
                        .eventType(EventType.MEETING)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://galatos.co.nz/wp-content/uploads/2021/06/70sPartyfacebook-Auckland.jpg")
                        .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                        .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                        .createdBy(bg)
                        .users(List.of(marioMileni))
                        .price(0)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(asc)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("Chicken Tueseday")
                        .eventType(EventType.SPECIAL_PROMOTION)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://brand-uk.assets.kfc.co.uk/styles/original/s3/2022-07/W6_22_WEBSITE_CAROUSEL_DESKTOP_CT_9OR_1900x725.jpg?itok=_8zk15M7")
                        .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                        .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                        .createdBy(bg)
                        .users(List.of(marioMileni))
                        .price(0)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(kfc)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("Zuvi - Promocija albuma 'Pozdrav Brate'")
                        .eventType(EventType.LIVE_MUSIC)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://dalmatinskiportal.hr/sadrzaj/vijesti/velika/2017-06-04-14-24-7817-.jpg")
                        .startingTime(new Date(123, Calendar.FEBRUARY, 20, 18, 0))
                        .endingTime(new Date(123, Calendar.FEBRUARY, 20, 23, 0))
                        .createdBy(bg)
                        .users(List.of(marioMileni))
                        .price(8)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(kvazi)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("80's Trash")
                        .eventType(EventType.SPECIAL_PROMOTION)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://dom-mladih.org/wp-content/uploads/2022/01/TRESIJA-22.01.2022.jpg")
                        .startingTime(new Date(123, Calendar.APRIL, 20, 23, 0))
                        .endingTime(new Date(123, Calendar.APRIL, 21, 4, 0))
                        .createdBy(jm)
                        .users(List.of(bg, jm))
                        .price(3)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(kocka)
                        .build());

        eventRepository.save(
                EventEntity.builder()
                        .name("Dirty Dancing")
                        .eventType(EventType.SPECIAL_PROMOTION)
                        .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.")
                        .imageUrl("https://www.split.com.hr/media/cache/632x/images/upload/c5/kvaz291019.jpeg")
                        .startingTime(new Date(123, Calendar.APRIL, 20, 23, 0))
                        .endingTime(new Date(123, Calendar.APRIL, 21, 4, 0))
                        .createdBy(jm)
                        .users(List.of(jm, marioMileni))
                        .price(3)
                        .currency(Currency.EUR)
                        .isRecurring(false)
                        .venue(kvazi)
                        .build());
    }
}
