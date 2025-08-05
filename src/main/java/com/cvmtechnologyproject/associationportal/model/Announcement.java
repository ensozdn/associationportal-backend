package com.cvmtechnologyproject.associationportal.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("ANNOUNCEMENT")
public class Announcement extends Event {

    private String imagePath;
}