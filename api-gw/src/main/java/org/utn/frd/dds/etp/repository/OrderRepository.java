package org.utn.frd.dds.etp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.utn.frd.dds.etp.entity.Order;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("select o from Order o where o.localDateTime = :date")
    Iterable<Order> findByDate(@Param("date") LocalDate date);

}
