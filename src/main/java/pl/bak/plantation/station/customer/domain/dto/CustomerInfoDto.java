package pl.bak.plantation.station.customer.domain.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerInfoDto {

    private Long id;
    public String name;
    public String surname;
    public String phone;
}
