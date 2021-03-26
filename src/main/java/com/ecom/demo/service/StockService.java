package com.ecom.demo.service;

import com.ecom.demo.dto.StockDetailsDTO;
import com.ecom.demo.dto.StockTransferDto;
import com.ecom.demo.entity.StockDetails;

import javassist.NotFoundException;

/**
 *
 * @author ronit.macwan
 *
 */
public interface StockService {

	void addStock(StockDetailsDTO stockDetailsDTO);

	void transferStock(StockTransferDto stockTransferDto) throws Exception;

	StockDetails getStockDetailByProduct(Long productId) throws NotFoundException;
}
