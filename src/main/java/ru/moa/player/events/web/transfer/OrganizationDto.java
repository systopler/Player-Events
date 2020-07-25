package ru.moa.player.events.web.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Long id;
    private String packName;
    private String fullName;
    private OrganizationTypeDto organizationTypeEntity;

    private LocalDateTime deleteDate;
    private Long version;

    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}
