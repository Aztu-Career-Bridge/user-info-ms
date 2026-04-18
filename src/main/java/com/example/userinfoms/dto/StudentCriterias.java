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

    // List<String> не поддерживается внутри @Embeddable,
    // поэтому храним как строку через запятую: "Backend,Frontend,DevOps"
    @Column(name = "preferred_categories")
    private String preferredCategories;

    private Boolean remoteAllowed;

}