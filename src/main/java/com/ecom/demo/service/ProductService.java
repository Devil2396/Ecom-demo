/**
 *
 */
package com.ecom.demo.service;

import java.util.List;

import javax.validation.ValidationException;

import com.ecom.demo.dto.ProductRequestDTO;
import com.ecom.demo.dto.ProductResponseDTO;
import com.ecom.demo.entity.Product;

import javassist.NotFoundException;

/**
 *
 * @author ronit.macwan
 *
 */
public interface ProductService {

	/**
	 *
	 * @param  productRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * @param  productId
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO getProduct(Long productId) throws NotFoundException, ValidationException;

	/**
	 * @param  productId
	 * @throws NotFoundException
	 */
	Product getProductDetails(Long productId) throws NotFoundException;

	/**
	 * Change status
	 *
	 * @param  productId
	 * @param  active
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO changeStatus(Long productId, Boolean active) throws NotFoundException, ValidationException;

	/**
	 * Update product
	 *
	 * @param  productRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	ProductResponseDTO updateProduct(ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * @param  activeRecords
	 * @param  searchKeyword
	 * @param  pageNumber
	 * @param  pageSize
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	List<ProductResponseDTO> getProductListBasedOnParams(Boolean activeRecords, String searchKeyword, Integer pageNumber, Integer pageSize)
			throws NotFoundException, ValidationException;

	/**
	 * @param  productRequestDTO
	 * @return
	 */
	boolean checkIfProductExists(ProductRequestDTO productRequestDTO);

	/**
	 * @param  categoryId
	 * @param  b
	 * @return
	 */
	List<Product> getProductByCategoryAndActive(Long categoryId, boolean b);
}
