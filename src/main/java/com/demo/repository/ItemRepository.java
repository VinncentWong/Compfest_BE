package com.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{
	Optional<Item> findByItemName(String name);
}
