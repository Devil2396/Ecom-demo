package com.ecom.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Product;
import com.ecom.demo.entity.StockDetails;

/**
 *
 * @author ronit.macwan
 *
 */
@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Long> {

	/**
	 * find by product
	 *
	 * @param  product
	 * @return
	 */
	Optional<StockDetails> findByProduct(Product product);

}
