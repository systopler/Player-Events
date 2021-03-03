package ru.moa.player.events.web.transfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SystemDto {
    private String email;
}
