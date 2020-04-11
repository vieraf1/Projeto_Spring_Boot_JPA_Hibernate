package com.thannos.projeto.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thannos.projeto.entities.Product;
import com.thannos.projeto.repositories.ProductRepository;
import com.thannos.projeto.services.exceptions.DatabaseException;
import com.thannos.projeto.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll() {
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Product insert(Product obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Product update(Long id, Product obj) {
		try {
			Product product = repository.getOne(id);
			updateData(product, obj);
			return repository.save(product);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Product product, Product obj) {
		if(obj.getDescription() != null) {
			product.setDescription(obj.getDescription());
		}
		if(obj.getImgUrl() != null) {
			product.setImgUrl(obj.getImgUrl());
		}
		if(obj.getName() != null) {
			product.setName(obj.getName());
		}
		if(obj.getPrice() != null) {
			product.setPrice(obj.getPrice());
		}
	}

}
