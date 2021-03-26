package com.ecom.demo.constant;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ecom.demo.dto.CustomerRequestDTO;
import com.ecom.demo.service.CustomerService;

@Component
public class CustomerValidator implements Validator {

	/**
	 * Locale message service - to display response messages from messages_en.properties
	 */
	@Autowired
	private CustomerService customerService;

	@Override
	public boolean supports(final Class<?> clazz) {
		return CustomerRequestDTO.class.equals(clazz);
	}

	@Override
	public void validate(final Object target, final Errors errors) {
		if (target instanceof CustomerRequestDTO) {
			final CustomerRequestDTO customerRequestDTO = (CustomerRequestDTO) target;

			try {
				if (customerRequestDTO.getContactNumber() != null && customerService.isExists(customerRequestDTO)) {
					errors.rejectValue("contactNumber", "409", "customer.contact.exists");
				}
			} catch (ValidationException e) {
				errors.rejectValue("contactNumber", "409", e.getMessage());
			}

			try {
				if (customerRequestDTO.getEmail() != null && customerService.isEmailExists(customerRequestDTO)) {
					errors.rejectValue("email", "409", "customer.email.exists");
				}
			} catch (ValidationException e) {
				errors.rejectValue("email", "409", e.getMessage());
			}
		}
	}
}