package com.ecom.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 *
 * @author ronit.macwan
 *
 */
@Data
public class CategoryDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7947238128297093026L;

	private Long id;

	@NotBlank(message = "{name.not.null}")
	private String name;

	private Boolean active;
}
