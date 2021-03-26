package com.ecom.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 * @author ronit.macwan
 *
 */
@Data
public class UOMDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -641864522538012591L;

	private Long id;

	@NotNull(message = "{store.id.not.null}")
	private Long storeId;

	@NotNull(message = "{uom.not.null}")
	private String uomLabel;

	private Boolean active;
}