package pl.bak.plantation.station.order.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderDto {
    public String additionalInfo;
    public Long CustomerId;
}
