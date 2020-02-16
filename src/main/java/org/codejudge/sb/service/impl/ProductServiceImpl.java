package org.codejudge.sb.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.codejudge.sb.api.ProductRepository;
import org.codejudge.sb.entity.Product;
import org.codejudge.sb.exception.RangeException;
import org.codejudge.sb.model.GenericResponseDto;
import org.codejudge.sb.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service
@Transactional(noRollbackFor = Exception.class, readOnly = true)
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository repo;

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public Product create(Product product) throws RangeException {
		log.info("input: " + product);
		Product.validateForUpsertion(product);
		Product savedProduct = repo.save(product);
		log.info("saved product successfully: " + product);
		return savedProduct;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public GenericResponseDto update(Integer id, Product product) throws RangeException {
		log.info("input id: " + id + ", input product: " + product);
		Product.validateForUpsertion(product);
		validateId(id);
		product.setId(id);
		Product savedProduct = get(id);
		if (null == savedProduct) {
			return null;
		}
		repo.save(product);
		return new GenericResponseDto("success");
	}

	@Override
	public Product get(Integer id) {
		log.info("input id: " + id );
		validateId(id);
		Product product = repo.getById(id);
		return product;
	}
	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = false)
	public GenericResponseDto delete(Integer id) throws FileNotFoundException {
		log.info("input id: " + id );
		Product product = get(id);
		if (product == null) {
			throw new FileNotFoundException("No Product found!");
		}
		repo.deleteById(id);
		return new GenericResponseDto();
	}
	
	private void validateId(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Product id can't be null!");
		}
	}
}
