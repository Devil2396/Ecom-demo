package com.ecom.demo.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecom.demo.dto.CategoryDTO;
import com.ecom.demo.service.CategoryService;

@Component
public class CategoryValidator implements Validator {

	@Autowired
	private CategoryService categoryService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return CategoryDTO.class.equals(clazz);
	}

	/**
	 * purpose - to validate object and apply various validations. this method may carry number of validation conditions.
	 */
	@Override
	public void validate(final Object target, final Errors errors) {
		final CategoryDTO categoryDTO = (CategoryDTO) target;
		/**
		 * to check category duplication
		 */
		if (categoryDTO.getName() != null && categoryService.isCategoryExists(categoryDTO)) {
			errors.rejectValue("name", "409", "category.name.not.unique");
		}
	}
}
