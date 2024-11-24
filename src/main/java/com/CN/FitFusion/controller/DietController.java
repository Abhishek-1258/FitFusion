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

import com.CN.FitFusion.dto.DietDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.service.DietService;

@RestController
@RequestMapping("/diet")
public class DietController {
	
	@Autowired
	DietService dietService;
	
	@PreAuthorize("hasRole('TRAINER')")
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Diet> getAllDiet(){
		return dietService.getAllDiet();
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Diet getDietById(@PathVariable Long id) {
		return dietService.getById(id);
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@PostMapping("/create/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createDietForUser(@RequestBody DietDto dietDto, @PathVariable Long userId) {
		dietService.createDietForUser(dietDto,userId);
	}
	
	
	@PreAuthorize("hasRole('TRAINER')")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateDietById(@RequestBody DietDto dietDto, @PathVariable Long id) {
		dietService.updateDietById(dietDto,id);
	}
	
	
	@PreAuthorize("hasRole('TRAINER')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteDietById(@PathVariable Long id) {
		dietService.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
