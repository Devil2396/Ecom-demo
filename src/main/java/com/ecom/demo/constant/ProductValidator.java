/**
 *
 */
package com.ecom.demo.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecom.demo.dto.ProductRequestDTO;
import com.ecom.demo.service.ProductService;

@Component
public class ProductValidator implements Validator {

	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return ProductRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {

		if (target instanceof ProductRequestDTO) {
			ProductRequestDTO productRequestDto = (ProductRequestDTO) target;

			if (productRequestDto.getName() != null && productService.checkIfProductExists(productRequestDto)) {
				errors.rejectValue("name", "409", "product.already.exists");
			}
		}

	}

}
