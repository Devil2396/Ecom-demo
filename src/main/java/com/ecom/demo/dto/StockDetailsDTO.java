package com.ecom.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author ronit.macwan
 *
 */
@Data
public class StockDetailsDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8845840813113451281L;
	private Long id;
	private Long productId;
	private Double available;
}
