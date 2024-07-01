package com.or.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.or.entity.Order;
import com.or.service.OrderBusiness;

@RestController
public class OrderController {
@Autowired
OrderBusiness orderBusiness;

@GetMapping("/greet")	
public ResponseEntity<String> greet() {
	return new ResponseEntity<String>("Welcome to Greet",HttpStatus.OK);
}
	
@GetMapping("/orders")
public ResponseEntity<List<Order>> getOrders(){
	return new ResponseEntity<List<Order>>(orderBusiness.getAllOrder(),HttpStatus.OK);
}

@GetMapping("/order/{oid}")
public ResponseEntity<Order> getOrderById(@PathVariable Integer oid){
	return new ResponseEntity<Order>(orderBusiness.getOrderById(oid), HttpStatus.OK);
}

@GetMapping("/orderBy/{uid}")
public ResponseEntity<List<Order>> getOrderByUid(@PathVariable Integer uid){
	return new ResponseEntity<List<Order>>(orderBusiness.getOrderByUid(uid), HttpStatus.OK);
}

@PostMapping("/save")
public ResponseEntity<Order> saveOrder(@RequestBody Order order){
	return new ResponseEntity<Order>(orderBusiness.saveOrder(order), HttpStatus.CREATED);
	
}

@PutMapping("/update/{uid}")
public ResponseEntity<Order> updateOrder(@PathVariable Integer id,@RequestBody Order order){
	return new ResponseEntity<Order>(orderBusiness.updateOrder(id, order),HttpStatus.ACCEPTED);
}

@PutMapping("/updateRefle/{uid}")
public ResponseEntity<Order> updateOrderReflection(@PathVariable Integer id,@RequestBody Map<String,String>fieldMap){
	return new ResponseEntity<Order>(orderBusiness.updateOrderReflect(id, fieldMap),HttpStatus.ACCEPTED);
}

@DeleteMapping("/delete")
public ResponseEntity<String> getDeleteOrder(Integer id){
	return new ResponseEntity<String>(orderBusiness.deleteOrderById(id),HttpStatus.OK);
}
	
	
	
}
