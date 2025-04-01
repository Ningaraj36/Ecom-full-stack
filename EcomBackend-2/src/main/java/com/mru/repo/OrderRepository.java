package com.mru.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mru.entity.CustomerOrder;
@Repository
public interface OrderRepository extends JpaRepository<CustomerOrder, Integer> {

}
