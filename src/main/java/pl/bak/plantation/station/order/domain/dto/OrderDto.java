package pl.bak.plantation.station.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    public Long id;
    public LocalDateTime creationTime;
    public LocalDateTime completedTime;
    public String additionalInfo;
    public boolean completed;
}
