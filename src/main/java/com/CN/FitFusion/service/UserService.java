package com.CN.FitFusion.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.UserDto;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.Role;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.UserRepository;


@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		if(users.isEmpty()) throw new UserNotFoundException("Empty Users");
		return users;
	}

	public User getById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
	}

	public void updateUserById(UserDto userDto, Long id) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userDto.getPassword());
		User existingUser = getById(id);
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(encodedPassword);
        existingUser.setAge(userDto.getAge());
        existingUser.setGender(userDto.getGender());
        existingUser.setContactNo(userDto.getContactNo());
        userRepository.save(existingUser);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public List<Exercise> getExercisesByUserId(Long id) {
		User user = getById(id);
		List<Exercise> exercises = user.getExerciseList();
		return exercises;
	}

	public List<Diet> getDietByUserId(Long id) {
		User user = getById(id);
		List<Diet> diets = user.getDiets();
		return diets;
	}
	
	public void registerUser(UserDto userDto) {
        User user = User.builder().email(userDto.getEmail()).age(userDto.getAge()).contactNo(userDto.getContactNo())
                .gender(userDto.getGender()).password(passwordEncoder.encode(userDto.getPassword()))
                .build();
        Set<Role> roleList = new HashSet<>();
        Role role = new Role();
        if(userDto.getUserType()!=null && userDto.getUserType().equalsIgnoreCase("ADMIN")){
            role.setRoleName("ROLE_ADMIN");
            roleList.add(role);
            user.setRoles(roleList);
        }else if(userDto.getUserType()!=null && userDto.getUserType().equalsIgnoreCase("TRAINER")){
            role.setRoleName("ROLE_TRAINER");
            roleList.add(role);
            user.setRoles(roleList);
        } else{
            role.setRoleName("ROLE_CUSTOMER");
            roleList.add(role);
            user.setRoles(roleList);
        }
        userRepository.save(user);
    }
	
	

	

}
