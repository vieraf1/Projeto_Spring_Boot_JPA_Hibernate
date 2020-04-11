package com.thannos.projeto.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.thannos.projeto.entities.Order;
import com.thannos.projeto.repositories.OrderRepository;
import com.thannos.projeto.repositories.UserRepository;
import com.thannos.projeto.services.exceptions.DatabaseException;
import com.thannos.projeto.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private UserRepository clientRepository;
	
	public List<Order> findAll() {
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Order insert(Order obj) {
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
	
	public Order update(Long id, Order obj) {
		try {
			Order order = repository.getOne(id);
			updateData(order, obj);
			return repository.save(order);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Order order, Order obj) {
		order.setMoment(obj.getMoment());
		order.setOrderStatus(obj.getOrderStatus());
		if(obj.getPayment() != null) {
			order.getPayment().setMoment(obj.getPayment().getMoment());
		}
		if(obj.getClient() != null) {
			order.setClient(clientRepository.getOne(obj.getClient().getId()));
		}
	}
}
