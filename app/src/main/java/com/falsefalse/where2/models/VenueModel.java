package com.falsefalse.where2.models;

import com.falsefalse.where2.persistence.entities.enums.VenueType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueModel {
    private int id;
    private VenueType venueType;
    private String name;
    private String workingHours;
    private UserModel owner;
    private String imageUrl;
}
