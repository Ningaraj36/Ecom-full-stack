package com.mru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mru.entity.Watches;
@Repository
public interface WatchesRepo extends JpaRepository<Watches, Integer> {

}
