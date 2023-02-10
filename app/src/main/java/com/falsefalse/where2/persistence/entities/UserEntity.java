package com.falsefalse.where2.persistence.entities;

import com.falsefalse.where2.persistence.entities.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String displayName;

    private String username;

    private String password;

    private String email;

    @ManyToMany(mappedBy = "followers")
    private List<VenueEntity> following;

    @ManyToMany
    @JoinTable(
            name = "user_event_entity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<EventEntity> events;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<VenueEntity> venues;

    @Enumerated(EnumType.STRING)
    private List<Role> roles;
}
