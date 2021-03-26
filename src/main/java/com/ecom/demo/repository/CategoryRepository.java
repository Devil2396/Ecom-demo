package com.ecom.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.demo.entity.Category;

/**
 *
 * @author ronit.macwan
 *
 */
@Repository(value = "categoryRepository")
public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryCustomRepository {

	/**
	 * Get Category page based on active field
	 *
	 * @param  active
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByActive(Boolean active, Pageable pageable);

	/**
	 * Get Category page based on name containing search keyword
	 *
	 * @param  searchKeyword
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByNameContainingIgnoreCase(String searchKeyword, Pageable pageable);

	/**
	 * Get Category page based on name containing search keyword and active
	 *
	 * @param  searchKeyword
	 * @param  active
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByNameContainingIgnoreCaseAndActive(String searchKeyword, Boolean active, Pageable pageable);

	/**
	 * Get Category based on name for not given id
	 *
	 * @param  name
	 * @param  category
	 * @param  id
	 * @return
	 */
	Optional<Category> findByNameIgnoreCaseAndIdNot(String name, Long id);

	/**
	 * Get Category based on name
	 *
	 * @param  name
	 * @param  category
	 * @return
	 */
	Optional<Category> findByNameIgnoreCase(String name);

	/**
	 * Get Category list based on name and active
	 *
	 * @param  active
	 * @return
	 */
	List<Category> findAllByActive(Boolean active);

	/**
	 * find by storeId and search and active
	 *
	 * @param  searchKeyword
	 * @param  activeRecords
	 * @param  storeId
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByNameContainingIgnoreCaseAndActiveAndStoreId(String searchKeyword, Boolean activeRecords, Long storeId, Pageable pageable);

	/**
	 * find by storeId and active
	 *
	 * @param  activeRecords
	 * @param  storeId
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByActiveAndStoreId(Boolean activeRecords, Long storeId, Pageable pageable);

	/**
	 * find by storeId and search
	 *
	 * @param  searchKeyword
	 * @param  storeId
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByNameContainingIgnoreCaseAndStoreId(String searchKeyword, Long storeId, Pageable pageable);

	/**
	 *
	 * find by storeId
	 *
	 * @param  storeId
	 * @param  pageable
	 * @return
	 */
	Page<Category> findAllByStoreId(Long storeId, Pageable pageable);

	/**
	 * get category based on storeId, name and id not
	 *
	 * @param  name
	 * @param  storeId
	 * @param  id
	 * @return
	 */
	Optional<Category> findByNameIgnoreCaseAndStoreIdAndIdNot(String name, Long storeId, Long id);

	/**
	 * get category based on storeId, name
	 *
	 * @param  name
	 * @param  storeId
	 * @return
	 */
	Optional<Category> findByNameIgnoreCaseAndStoreId(String name, Long storeId);

}
