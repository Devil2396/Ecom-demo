package com.ecom.demo.dto;

import java.io.Serializable;

import lombok.Data;

/**
 *
 * @author ronit.macwan
 *
 */
@Data
public class StockResponseDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8845840813113451281L;
	private Long id;
	private Long productId;
	private Double available;
	private Double reserved;
	private Double delivered;
	private String productName;
	private Long uomId;
	private String uomLabel;
}
