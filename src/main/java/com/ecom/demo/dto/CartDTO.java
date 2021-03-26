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
public class CartDTO implements Serializable {

	/**
	*
	*/
	private static final long serialVersionUID = 2920180246659376736L;

	private Long id;

	@NotNull(message = "Customer is required")
	private Long customerId;

	@NotNull(message = "Product is required")
	private Long productId;

	@NotNull(message = "Qty is reqiured")
	private Long quantity;

	private String productName;

	private Long uomId;

	private String uomName;
}