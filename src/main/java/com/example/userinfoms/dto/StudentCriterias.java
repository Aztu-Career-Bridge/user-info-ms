package com.example.userinfoms.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCriterias {

    private String desiredLevel;

    @Column(name = "preferred_categories")
    private String preferredCategories;

    private Boolean remoteAllowed;

}