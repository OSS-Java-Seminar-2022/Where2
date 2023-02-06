package com.falsefalse.where2.persistence.entities;

import com.falsefalse.where2.persistence.entities.enums.Currency;
import com.falsefalse.where2.persistence.entities.enums.EventType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //image gallery
    //event banner
    private Date startingTime;
    private Date endingTime;
    @ManyToMany(mappedBy = "subscribedTo")
    private List<UserEntity> subscribedUsers;
    private float price;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private boolean isRecurring;

    public void subscribe(UserEntity user){
        this.getSubscribedUsers().add(user);
        user.getSubscribedTo().add(this);
    }
}
