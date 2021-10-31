package org.utn.frd.dds.etp.repository;

import org.utn.frd.dds.etp.entity.Order;

import java.time.LocalDate;
import java.util.Optional;

public class OrderRepository {
    
    public Optional<Order> findOrderById(String id) {

        return null;
    }

    public void add(Object pedido) {
    }

    public void delete(String uuid) {
    }

    public Iterable<Object> findAll() {

        return null;
    }

    public Iterable<Object> findPorDate(LocalDate date) {

        return null;
    }


}
