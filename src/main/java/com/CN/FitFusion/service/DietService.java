package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.exception.DietNotFoundException;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.DietRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class DietService {
	
	@Autowired
	DietRepository dietRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<Diet> getAllDiet() {
		List<Diet> diets = dietRepository.findAll();
		if(diets.isEmpty()) {
			throw new DietNotFoundException("No Diet Found");
		}
		return diets;
	}

	public Diet getById(Long id) {
		Diet diet = dietRepository.findById(id).orElseThrow(()-> new DietNotFoundException("Diet with " + id + " was not found"));
		return diet;
	}

	public void createDietForUser(DietDto dietDto, Long userId) {
		Diet diet = Diet.builder().name(dietDto.getName()).description(dietDto.getDescription()).build();
		User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		user.getDiets().add(diet);
		userRepository.save(user);
	}

	public void updateDietById(DietDto dietDto, Long id) {
		Diet existingDiet = getById(id);
		existingDiet.setName(dietDto.getName());
		existingDiet.setDescription(dietDto.getDescription());
		
		dietRepository.save(existingDiet);
		
	}

	public void deleteById(Long id) {
		dietRepository.deleteById(id);
	}
	
	

}
