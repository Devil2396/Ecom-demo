package com.ecom.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ecom.demo.dto.CategoryDTO;
import com.ecom.demo.entity.Category;

/**
 *
 * @author ronit.macwan
 *
 */
@Component
public class CategoryMapper {

	public CategoryDTO toDto(final Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		BeanUtils.copyProperties(category, categoryDTO);
		return categoryDTO;
	}

	public Category toEntity(final CategoryDTO categoryDTO) {
		Category category = new Category();
		BeanUtils.copyProperties(categoryDTO, category);
		return category;
	}

	public List<CategoryDTO> toDtos(final List<Category> categoryList) {
		List<CategoryDTO> results = new ArrayList<>();
		for (Category category : categoryList) {
			results.add(toDto(category));
		}
		return results;
	}
}
