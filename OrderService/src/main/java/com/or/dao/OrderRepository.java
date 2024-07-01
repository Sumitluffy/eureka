package com.or.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.or.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
public List<Order> findByUid(Integer uid);
}
