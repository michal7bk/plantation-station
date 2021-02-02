package pl.bak.plantation.station.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import pl.bak.plantation.station.customer.domain.Customer;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    public Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime creationTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime completedTime;

    public String additionalInfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer customer;

    @Builder.Default
    public boolean completed = false;


}
