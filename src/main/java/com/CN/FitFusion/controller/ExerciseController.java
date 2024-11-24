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
import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.model.Diet;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.service.ExerciseService;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
	
	@Autowired
	ExerciseService exerciseService;

	@PreAuthorize("hasRole('TRAINER')")
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.OK)
	public List<Exercise> getAllExercise(){
		return exerciseService.getAllExercise();
		
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Exercise getDietById(@PathVariable Long id) {
		return exerciseService.getById(id);
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@PostMapping("/create/{userId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void createExerciseForUser(@RequestBody ExerciseDto exerciseDto, @PathVariable Long userId) {
		exerciseService.createExerciseForUser(exerciseDto,userId);
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void updateExerciseById(@RequestBody ExerciseDto exerciseDto, @PathVariable Long id) {
		exerciseService.updateExerciseById(exerciseDto,id);
	}
	
	@PreAuthorize("hasRole('TRAINER')")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteExerciseById(@PathVariable Long id) {
		exerciseService.deleteById(id);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
