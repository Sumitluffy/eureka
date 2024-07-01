package com.prac.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import com.prac.config.RestConfig;
import com.prac.dao.UserRepository;
import com.prac.dto.Order;
import com.prac.dto.UserDto;
import com.prac.entity.User;
import com.prac.exception.RecordNotFoundException;
import com.prac.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	UserRepository ur;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${orderservice.url}")
	String orderserviceurl;
	
	public List<User> getAllUser(){
		return ur.findAll();
	}
	
	
	public User getUserById(Integer id) {
		return ur.findById(id).orElseThrow(()->new UserNotFoundException("User not found"));
	}
	
	public User getUserByName(String name) {
		return ur.findByUname(name);
	}
	
	public User getUserByEmail(String email) {
		return ur.getUserEmail(email);
	}
	
	public User addUser(User user) {
		return ur.save(user);
	}
	
	public User updateUser(Integer id,User user) {
	User existingUser=getUserById(id);	
		if(existingUser!=null) {
			existingUser.setUname(user.getUname()!=null?user.getUname():existingUser.getUname());
			existingUser.setAddress(user.getAddress()!=null?user.getAddress():existingUser.getAddress());
			existingUser.setEmail(user.getEmail()!=null?user.getEmail():existingUser.getEmail());
			existingUser.setPassword(user.getPassword()!=null?user.getPassword():existingUser.getPassword());
			
		}
		
		return ur.save(existingUser);
		
	}
	
	public User updateUserReflectionApi(Integer id,Map<String,String>fieldMap) {
		User existingUser=getUserById(id);
		fieldMap.forEach((k,v)->{
		Field field=ReflectionUtils.findField(User.class,k);
		field.setAccessible(true);
		ReflectionUtils.setField(field, existingUser, v);
		
		});
		return ur.save(existingUser);
		
	}
	public String deleteUserByName(String name) {
		ur.deleteUserByName(name);
		return "User delete of name : "+name;
	}
	public String deleteUserById(Integer id) {
		ur.deleteById(id);
	return "User delete of id : "+id;
	}
	
	
	public List<User> getUserByPage(Integer pageSize,Integer noOfPages){
		Pageable pageable=PageRequest.of(pageSize, noOfPages,Sort.by("uname").descending());

	//	Pageable pageable=PageRequest.of(pageSize, noOfPages,Sort.by("uname").descending());
		Page<User> page=ur.findAll(pageable);
		if(page.isEmpty()) {
			throw new RecordNotFoundException("Norecord found in this page");
		}
		return page.toList();
		
	}
	
	public List<User> getSortedUser(String field,String sortType){
		return sortType.equals("desc")?ur.findAll(Sort.by(field).descending()):ur.findAll(Sort.by(field));
	}
	
	
	//Connecting OrderService i.e drive into microservices
	
	public String greetFromOrderService() {
		//return restTemplate.getForObject("http://localhost:7075/greet", String.class);
		return restTemplate.getForObject(orderserviceurl+"/greet", String.class);
		                           //orderserviceurl+"/greet", String.class
	
	}
	public List<Order> getOrdersOfUser(Integer uid){
		return restTemplate.getForObject(orderserviceurl+"/orderBy/"+uid, List.class);
		//                               orderserviceurl+"/orders/"+uid, List.class
	}
	
	public UserDto getOrdersOfSingleUser( Integer uid) {
		UserDto userDto=new UserDto();
		User user =getUserById(uid);
		userDto.setUid(uid);
		userDto.setUname(user.getUname());		
		userDto.setEmail(user.getEmail());
		userDto.setAddress(user.getAddress());
		userDto.setPassword(user.getPassword());
		userDto.setPayment(user.getPayment());
		
		List<Order>orders= restTemplate.getForObject(orderserviceurl+"/orderBy/"+uid, List.class);
	userDto.setOrders(orders);	
	return userDto;
	}
	
}
