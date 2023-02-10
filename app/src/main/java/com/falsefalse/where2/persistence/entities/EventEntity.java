package com.falsefalse.where2.persistence.entities;

import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    // notifications
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    private String imageUrl;
    //image gallery
    //event banner
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startingTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endingTime;
    @ManyToMany(mappedBy = "events")
    private List<UserEntity> users;
    private float price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private boolean isRecurring;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
    private String description;
    @ManyToOne
    @PrimaryKeyJoinColumn
    @JoinColumn(name = "venue", nullable = false)
    private VenueEntity venue;

    public void subscribe(UserEntity user) {
        this.getUsers().add(user);
        user.getEvents().add(this);
    }
}
