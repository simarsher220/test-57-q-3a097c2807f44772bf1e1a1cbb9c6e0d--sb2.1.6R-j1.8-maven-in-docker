package org.codejudge.sb.service.api;

import org.codejudge.sb.entity.Product;
import org.codejudge.sb.exception.RangeException;
import org.codejudge.sb.model.GenericResponseDto;

import java.io.FileNotFoundException;

public interface ProductService {
	public Product create(Product product) throws RangeException;
	public GenericResponseDto update(Integer id, Product product) throws FileNotFoundException, RangeException;
	public Product get(Integer id) throws FileNotFoundException;
	public GenericResponseDto delete(Integer id) throws FileNotFoundException;
}
