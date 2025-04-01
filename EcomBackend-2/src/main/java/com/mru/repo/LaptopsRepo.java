package com.mru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mru.entity.Laptops;
@Repository
public interface LaptopsRepo extends JpaRepository<Laptops, Integer> {

}
