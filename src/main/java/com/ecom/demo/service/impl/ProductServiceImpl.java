/**
 *
 */
package com.ecom.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.demo.dto.ProductRequestDTO;
import com.ecom.demo.dto.ProductResponseDTO;
import com.ecom.demo.entity.Product;
import com.ecom.demo.mapper.ProductMapper;
import com.ecom.demo.repository.ProductRepository;
import com.ecom.demo.service.ProductService;

import javassist.NotFoundException;

/**
 *
 * @author ronit.macwan
 *
 */
@Transactional(rollbackFor = Throwable.class)
@Service(value = "productServiceImpl")
public class ProductServiceImpl implements ProductService {

	private static final String PRODUCT_NOT_FOUND = "product.not.found";

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private ProductRepository productRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public ProductResponseDTO addProduct(final ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException {
		Product product = createProduct(productRequestDTO);
		return convertToResponseDto(product);
	}

	/**
	 * @param  productRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	private Product createProduct(final ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException {

		Product product = productMapper.toEntity(productRequestDTO);
		product.setActive(true);
		validationForActivateProduct(product);

		product = productRepository.save(product);
		return product;
	}

	@Override
	public ProductResponseDTO updateProduct(final ProductRequestDTO productRequestDTO) throws NotFoundException, ValidationException {

		if (productRequestDTO.getId() == null) {
			throw new ValidationException("product.is.required");
		}

		Product exisitngProduct = productRepository.findById(productRequestDTO.getId()).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));

		Product product = productMapper.toEntity(productRequestDTO);
		product.setActive(exisitngProduct.getActive());

		productRepository.save(product);
		return convertToResponseDto(product);
	}

	public void validationForActivateProduct(final Product existingProduct) throws NotFoundException, ValidationException {
		LOGGER.info("Inside validation for activate product for Product :{}", existingProduct.getId());

		if (Boolean.FALSE.equals(existingProduct.getCategory().getActive())) {
			throw new ValidationException("category.activate.first.product");
		}

		LOGGER.info("After validation for activate product for Product :{}", existingProduct.getId());
	}

	@Override
	public ProductResponseDTO getProduct(final Long productId) throws NotFoundException, ValidationException {
		return convertToResponseDto(getProductDetails(productId));
	}

	@Override
	public List<ProductResponseDTO> getProductListBasedOnParams(final Boolean activeRecords, final String searchKeyword, final Integer pageNumber,
			final Integer pageSize) throws NotFoundException, ValidationException {
		Page<Product> productPage;
		Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, Sort.by("id"));
		if (activeRecords != null) {
			if (searchKeyword != null) {
				productPage = productRepository.findAllByActiveAndNameContainingIgnoreCase(activeRecords, searchKeyword, pageable);
			} else {
				productPage = productRepository.findAllByActive(activeRecords, pageable);
			}
		} else {
			if (searchKeyword != null) {
				productPage = productRepository.findAllByNameContainingIgnoreCase(searchKeyword, pageable);
			} else {
				productPage = productRepository.findAll(pageable);
			}
		}

		List<ProductResponseDTO> productResponseDtoList = new ArrayList<>();
		for (Product product : productPage.getContent()) {
			ProductResponseDTO productResponseDto = convertToResponseDto(product);
			productResponseDtoList.add(productResponseDto);
		}
		return productResponseDtoList;
	}

	@Override
	public boolean checkIfProductExists(final ProductRequestDTO productRequestDTO) {

		if (productRequestDTO.getId() != null) {
			return productRepository.findByVendorIdAndNameAndIdNot(productRequestDTO.getVendorId(), productRequestDTO.getName(), productRequestDTO.getId())
					.isPresent();
		} else {
			return productRepository.findByVendorIdAndName(productRequestDTO.getVendorId(), productRequestDTO.getName()).isPresent();
		}

	}

	@Override
	public Product getProductDetails(final Long productId) throws NotFoundException {
		return productRepository.findById(productId).orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));
	}

	@Override
	public ProductResponseDTO changeStatus(final Long productId, final Boolean active) throws NotFoundException, ValidationException {
		LOGGER.info("Inside changeStatus for Product :{}, status :{}", productId, active);
		Product product = getProductDetails(productId);
		if (active == null) {
			throw new ValidationException("active.not.null");
		} else if (product.getActive().equals(active)) {
			throw new ValidationException("product.active.deactive".concat(Boolean.TRUE.equals(active) ? "active" : "deActive"));
		} else {
			changeStatusForDependentEntity(active, product);
			product.setActive(active);
			productRepository.save(product);
		}
		LOGGER.info("After changeStatus for Product :{}, status :{}", productId, active);
		return convertToResponseDto(product);

	}

	/**
	 *
	 * @param  active
	 * @param  existingProduct
	 * @return
	 * @throws ValidationException
	 *
	 */
	public ProductResponseDTO changeStatusForDependentEntity(final Boolean active, final Product existingProduct) throws ValidationException {
		if (Boolean.TRUE.equals(active) && Boolean.FALSE.equals(existingProduct.getCategory().getActive())) {
			throw new ValidationException("category.activate.first.product");
		}
		return convertToResponseDto(existingProduct);
	}

	private ProductResponseDTO convertToResponseDto(final Product product) {
		ProductResponseDTO productResponseDTO = productMapper.toResponseDto(product);
		productResponseDTO.setCategoryId(product.getCategory().getId());
		productResponseDTO.setCategoryName(product.getCategory().getName());
		productResponseDTO.setVendorId(product.getVendor().getId());
		productResponseDTO.setVendorName(product.getVendor().getName());
		return productResponseDTO;
	}

	@Override
	public List<Product> getProductByCategoryAndActive(final Long categoryId, final boolean b) {
		return productRepository.findAllByCategoryIdAndActive(categoryId, b);
	}
}
