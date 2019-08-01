package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    Long countByUserIdEqualsAndStatusEquals(long userid, int status);
    Long countByStatusEquals(int status);

}
