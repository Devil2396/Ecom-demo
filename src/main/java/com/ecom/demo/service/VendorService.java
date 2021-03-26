package com.ecom.demo.service;

import java.util.Map;

import javax.validation.ValidationException;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.demo.dto.VendorRequestDTO;
import com.ecom.demo.dto.VendorResponseDTO;
import com.ecom.demo.entity.Vendor;

import javassist.NotFoundException;

/**
 *
 * @author ronit.macwan
 *
 */
public interface VendorService {
	/**
	 * Add vendor
	 *
	 * @param  vendorRequestDTO
	 * @param  storeImages
	 * @return
	 * @throws ValidationException
	 * @throws NotFoundException
	 * @throws FileOperationException
	 */
	VendorResponseDTO addVendor(VendorRequestDTO vendorRequestDTO, Map<String, MultipartFile> storeImages) throws ValidationException, NotFoundException;

	/**
	 * update vendor's personal details
	 *
	 * @param  vendorRequestDTO
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 */
	void updatePersonalDetails(VendorRequestDTO vendorRequestDTO) throws NotFoundException, ValidationException;

	/**
	 * Get detail object of vendor
	 *
	 * @param  vendorId
	 * @return
	 * @throws NotFoundException
	 */
	Vendor getVendorDetails(Long vendorId) throws NotFoundException;

	/**
	 * Change status of vendor
	 *
	 * @param  vendorId
	 * @param  newStatus
	 * @return
	 * @throws NotFoundException
	 * @throws ValidationException
	 * @throws ParseException
	 */
	Vendor changeVendorStatus(Long vendorId, String newStatus) throws NotFoundException, ValidationException;

	/**
	 * Get vendor page based on parameters
	 *
	 * @param  pageNumber
	 * @param  pageSize
	 * @param  status
	 * @param  searchKeyword
	 * @return
	 */
	Page<Vendor> getVendorList(Integer pageNumber, Integer pageSize, String status, final String searchKeyword);

	/**
	 * Get vendor by id
	 *
	 * @param  vendorId
	 * @return
	 * @throws NotFoundException
	 */
	VendorResponseDTO getVendor(Long vendorId) throws NotFoundException;
}
