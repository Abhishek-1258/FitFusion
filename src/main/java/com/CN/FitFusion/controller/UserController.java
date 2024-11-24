package com.CN.FitFusion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getAllExercise(){
		return userService.getAllUsers();
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable Long id) {
		return userService.getById(id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateUserById(@RequestBody UserDto userDto, @PathVariable Long id) {
		userService.updateUserById(userDto,id);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteById(id);
	}
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/exercise/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Exercise> getExercisesByUserId(@PathVariable Long id) {
		return userService.getExercisesByUserId(id);
	}
	
	
	@PreAuthorize("hasRole('CUSTOMER')")
	@GetMapping("/diet/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Diet> getDietByUserId(@PathVariable Long id) {
		return userService.getDietByUserId(id);
	}
	
	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerUser(@RequestBody UserDto userDto) {
		userService.registerUser(userDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
