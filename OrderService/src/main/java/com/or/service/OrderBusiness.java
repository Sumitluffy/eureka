package com.or.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
import com.or.dao.OrderRepository;
import com.or.entity.Order;
import com.or.exception.OrderNotFoundException;

@Service
public class OrderBusiness {
	@Autowired
	OrderRepository orderRepository;
	
	
	public List<Order> getAllOrder(){
		return orderRepository.findAll();
		
	}
	
	public List<Order> getOrderByUid(Integer id){
		return orderRepository.findByUid(id);
	}
	public Order getOrderById(Integer id) {
	return	orderRepository.findById(id).orElseThrow(()->new OrderNotFoundException("Order is not in record"));
		
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public Order updateOrder(Integer id,Order order) {
		Order existingOrder=getOrderById(id);
		if(existingOrder!=null) {
			existingOrder.setUid(order.getUid()!=null?order.getUid():existingOrder.getUid());
			existingOrder.setCategory(order.getCategory()!=null?order.getCategory():existingOrder.getCategory());
			existingOrder.setStatus(order.getStatus()!=null?order.getStatus():existingOrder.getStatus());
			
		}
		return orderRepository.save(existingOrder);
		
		}
	
	public Order updateOrderReflect(Integer id,Map<String,String>fieldMap) {
		Order existingOrder=getOrderById(id);
		fieldMap.forEach((k,v)->{
			Field field=ReflectionUtils.findField(Order.class, k);
			
			field.setAccessible(true);
			ReflectionUtils.setField(field, existingOrder, v);
		});
		return orderRepository.save(existingOrder);
		
	}
	
	public String deleteOrderById(Integer id) {
		orderRepository.deleteById(id);
		return "Order with "+id+" is deleted";
	}
	
	
	
	
	
	
	
	

}
