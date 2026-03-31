package com.hexaware.careercrafter.repository;

import com.hexaware.careercrafter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    /*
     JpaRepository
        save(T) : T
        findAll() : List<T>
        findById(id) : T
        deleteById(id)
        saveAll(list)
    */

    Optional<User> findByEmail(String email);

    @Query("""
            select u
            from User u
            where u.email = ?1
            """)
    Optional<User> getUserByEmail(String email);
}
