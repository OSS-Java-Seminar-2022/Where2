package com.falsefalse.where2.persistence.entities;

import com.falsefalse.where2.persistence.entities.enums.VenueType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity()
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Enumerated(EnumType.STRING)
    private VenueType venueType;
    private String name;
    private String workingHours;
    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private UserEntity owner;
    private String imageUrl;
    @OneToMany(mappedBy = "venue", cascade = CascadeType.REMOVE)
    private List<EventEntity> events;

    @ManyToMany
    @JoinTable(
            name = "user_venue_entity",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "venue_id"))
    private List<VenueEntity> followers;
}
