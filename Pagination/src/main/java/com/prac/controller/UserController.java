package com.prac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prac.config.RestConfig;
import com.prac.dto.Order;
import com.prac.dto.UserDto;
import com.prac.entity.User;
import com.prac.service.UserService;
import java.util.*;

@RestController
public class UserController {

	@Autowired
	UserService us;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUser() {
		return new ResponseEntity<List<User>>(us.getAllUser(), HttpStatus.OK);
	}

	@GetMapping("/user/{uid}")
	public ResponseEntity<User> getUserById(@PathVariable Integer uid) {
		return new ResponseEntity<User>(us.getUserById(uid), HttpStatus.OK);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByName(@PathVariable String username) {
		return new ResponseEntity<User>(us.getUserByName(username), HttpStatus.OK);
	}

	@GetMapping("/useremail/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		return new ResponseEntity<User>(us.getUserByEmail(email), HttpStatus.OK);
	}
	
	

	

	@PostMapping("/save")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		return new ResponseEntity<User>(us.addUser(user), HttpStatus.CREATED);
	}

	@PutMapping("/updateuser/{uid}")
	public ResponseEntity<User> modifyUser(@PathVariable Integer uid, @RequestBody User user) {
		return new ResponseEntity<User>(us.updateUser(uid, user), HttpStatus.ACCEPTED);
	}

	@PutMapping("/updatereflection/{uid}")
	public ResponseEntity<User> modifyUserReflection(@PathVariable Integer uid,@RequestBody Map<String, String> fieldMap) {
		return new ResponseEntity<User>(us.updateUserReflectionApi(uid, fieldMap), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/deletebyid/{uid}")
	public ResponseEntity<String> userDeleteById(@PathVariable Integer uid) {
		return new ResponseEntity<String>(us.deleteUserById(uid), HttpStatus.OK);
	}

	@DeleteMapping("/deletebyname/{uname}")
	public ResponseEntity<String> deleteUserByName(@PathVariable String uname) {
		return new ResponseEntity<String>(us.deleteUserByName(uname), HttpStatus.OK);
	}
	@GetMapping("/userpage/{pagesize}/{noofpage}")
    public ResponseEntity<List<User>> getUserByPage(@PathVariable Integer pagesize,@PathVariable Integer noofpage){
		return new ResponseEntity<List<User>>(us.getUserByPage(pagesize, noofpage),HttpStatus.OK);
}
	@GetMapping("/usersort/{field}/{sortType}")
	public ResponseEntity<List<User>> getUserSort(@PathVariable String field,@PathVariable String sortType){
		return new ResponseEntity<List<User>>(us.getSortedUser(field, sortType), HttpStatus.OK);
	}
	
	
	//Connecting OrderService i.e drive into microservices
	
	@GetMapping("/ordergreet")
	public ResponseEntity<?> greetFromOrderService(){
		return new ResponseEntity<String>(us.greetFromOrderService(), HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/orders/{uid}")
	public ResponseEntity<List<Order>> getOrdersOfUser(@PathVariable Integer uid){
		return new ResponseEntity<List<Order>>(us.getOrdersOfUser(uid), HttpStatus.OK);
//	public List<Order> getOrdersOfUser(@PathVariable Integer uid){
	//	return restTemplate.getForObject("http://localhost:7075/orderBy/"+uid, List.class);
	
}
	@GetMapping("/user/orders/{uid}")
	public ResponseEntity<UserDto> getOrdersOfSingleUser(@PathVariable Integer uid){
		return new ResponseEntity<UserDto>(us.getOrdersOfSingleUser(uid), HttpStatus.OK);
	}
	
}
