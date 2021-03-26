package com.ecom.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ecom.demo.dto.ProductRequestDTO;
import com.ecom.demo.dto.ProductResponseDTO;
import com.ecom.demo.entity.Product;

/**
 *
 * @author ronit.macwan
 *
 */
@Component
public class ProductMapper {
	public ProductRequestDTO toRequestDto(final Product product) {
		ProductRequestDTO productRequestDTO = new ProductRequestDTO();
		BeanUtils.copyProperties(product, productRequestDTO);
		return productRequestDTO;
	}

	public ProductResponseDTO toResponseDto(final Product product) {
		ProductResponseDTO productResponseDTO = new ProductResponseDTO();
		BeanUtils.copyProperties(product, productResponseDTO);
		return productResponseDTO;
	}

	public Product toEntity(final ProductRequestDTO productRequestDTO) {
		Product product = new Product();
		BeanUtils.copyProperties(productRequestDTO, product);
		return product;
	}

	public List<ProductResponseDTO> toResponseDtos(final List<Product> products) {
		List<ProductResponseDTO> results = new ArrayList<>();
		for (Product product : products) {
			results.add(toResponseDto(product));
		}
		return results;
	}
}
