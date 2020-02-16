package org.codejudge.sb.controller;

import org.codejudge.sb.entity.Product;
import org.codejudge.sb.model.GenericResponseDto;
import org.codejudge.sb.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class ProductController {

	@Autowired
    ProductService service;
	
	@GetMapping("/")
	@ResponseBody
	public String test() throws IOException {
		return "Hello Docker world!";
	}

	@PostMapping("/api/products")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public Product create(@RequestBody Product product) throws Exception{
		return service.create(product);
	}
	
	@PutMapping("/api/products/{id}")
	@ResponseBody
	public Object update(@PathVariable(value = "id") Integer id, @RequestBody Product product) throws Exception {
		GenericResponseDto resp = service.update(id, product);
		if (resp == null) {
			throw new FileNotFoundException("No product found!");
		}
		return resp;
	}
	
	@GetMapping("/api/products/{id}")
	@ResponseBody
	public Object get(@PathVariable(value = "id") Integer id) throws Exception {
		Product product = service.get(id);
		if (null == product) {
			throw new FileNotFoundException("Product not found!");
		}
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("data", product);
		return new ResponseEntity<Object>(product, HttpStatus.OK);
	}
	
	@DeleteMapping("/api/products/{id}")
	@ResponseBody
	public GenericResponseDto delete(@PathVariable(value = "id") Integer id) throws Exception {
		return service.delete(id);
	}
}
