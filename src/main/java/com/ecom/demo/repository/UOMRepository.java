package com.ecom.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.UOM;

/**
 *
 * @author ronit.macwan
 *
 */
@Repository
public interface UOMRepository extends JpaRepository<UOM, Long> {

	/**
	 * Get UOM by uom measurement and uom Id not equal if exist
	 *
	 * @param  measurement
	 * @param  id
	 * @return
	 */
	Optional<UOM> findByUomLabelIgnoreCaseAndIdNot(String uomLabel, Long id);

	/**
	 * Get UOM Page by active
	 *
	 * @param  pageable
	 * @param  active
	 * @return
	 */
	Page<UOM> findAllByActive(Boolean active, Pageable pageable);

	/**
	 * Get UOM list by active
	 *
	 * @param  active
	 * @return
	 */
	List<UOM> findAllByActive(Boolean active);

	/**
	 * Get UOM by uom measurement if exist
	 *
	 * @param  uomLabel
	 * @return
	 */
	Optional<UOM> findByUomLabelIgnoreCase(String uomLabel);

}