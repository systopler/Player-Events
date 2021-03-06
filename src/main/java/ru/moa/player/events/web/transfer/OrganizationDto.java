package ru.moa.player.events.web.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationDto {
    private Long id;
    private String packName;
    private String fullName;
    private OrganizationTypeDto organizationType;
    private List<OrganizationAliasDto> aliases;
    private List<OrganizationColorDto> colors;

    private LocalDateTime deleteDate;
    private Long objectVersionNumber;

    private Long createdBy;
    private LocalDateTime creationDate;
    private Long lastUpdatedBy;
    private LocalDateTime lastUpdateDate;
}
