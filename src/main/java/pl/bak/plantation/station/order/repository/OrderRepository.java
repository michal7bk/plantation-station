package pl.bak.plantation.station.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bak.plantation.station.order.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
