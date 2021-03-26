package com.ecom.demo.service;

import java.util.List;

import com.ecom.demo.dto.CartDTO;

import javassist.NotFoundException;

/**
 *
 * @author ronit.macwan
 *
 */
public interface CartService {

	void addCart(CartDTO cartDTO) throws NotFoundException;

	void deleteCartById(Long cartId) throws NotFoundException;

	List<CartDTO> getCustomerCartList(Long customerId) throws NotFoundException;
}
