package com.endava.exam.repository;

import com.endava.exam.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {

    boolean existsByName(String name);

    boolean existsById(String id);

    Supermarket findSupermarketById(String id);
}
