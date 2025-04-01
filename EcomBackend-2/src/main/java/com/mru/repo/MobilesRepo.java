package com.mru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mru.entity.Mobiles;
@Repository
public interface MobilesRepo extends JpaRepository<Mobiles, Integer> {

}
