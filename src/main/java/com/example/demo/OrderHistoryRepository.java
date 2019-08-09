package com.example.demo;

import org.hibernate.criterion.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;


public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    Long countByStatusEquals(int status);
    Long countByOrduserEqualsAndStatusEquals(User userid, int status);

    ArrayList<OrderHistory> findAllByStatusEquals(int status);
    ArrayList<OrderHistory> findAllByStatusEqualsOrderByOrderId(int status);
    ArrayList<OrderHistory> findAllByOrduserEqualsAndStatusEqualsOrderByOrderId(User user, int status);

    ArrayList<OrderHistory> findAllByOrderIdEquals(String orderid);
    OrderHistory findByOrderIdEquals(String orderid);

    ArrayList<OrderHistory> findAllByOrderIdEqualsAndStatusEquals(long id, int status);


}
