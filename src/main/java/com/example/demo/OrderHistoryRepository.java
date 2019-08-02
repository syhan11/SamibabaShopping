package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    Long countByStatusEquals(int status);
    ArrayList<OrderHistory> findAllByStatus(int status);
    Long countByOrduserEqualsAndStatusEquals(User tmpuser, int status);


}
