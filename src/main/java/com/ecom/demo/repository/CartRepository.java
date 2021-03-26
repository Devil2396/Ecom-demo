package com.ecom.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Cart;
import com.ecom.demo.entity.Customer;

/**
 *
 * @author ronit.macwan
 *
 */
@Repository(value = "cartRepository")
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByCustomer(Customer customer);
}
