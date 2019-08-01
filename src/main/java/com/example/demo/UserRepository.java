package com.example.demo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);

    Long countByUsernameAndRolesContains(String username, String role);

}
