package com.CN.FitFusion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CN.FitFusion.dto.ExerciseDto;
import com.CN.FitFusion.exception.ExerciseNotFoundException;
import com.CN.FitFusion.exception.UserNotFoundException;
import com.CN.FitFusion.model.Exercise;
import com.CN.FitFusion.model.User;
import com.CN.FitFusion.repository.ExerciseRepository;
import com.CN.FitFusion.repository.UserRepository;

@Service
public class ExerciseService {
	
	@Autowired
	ExerciseRepository exerciseRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<Exercise> getAllExercise() {
		List<Exercise> exercises = exerciseRepository.findAll();
		if(exercises.isEmpty()) throw new ExerciseNotFoundException("Not Found");
		return exercises; 
	}

	public Exercise getById(Long id) {
		return exerciseRepository.findById(id).orElseThrow(() -> new ExerciseNotFoundException("Not Found"));
	}

	public void createExerciseForUser(ExerciseDto exerciseDto, Long userId) {
		Exercise exercise = Exercise.builder().name(exerciseDto.getName())
				.description(exerciseDto.getDescription())
				.sets(exerciseDto.getSets())
				.reps(exerciseDto.getReps()).build();
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User Not Found"));
		user.getExerciseList().add(exercise);
		userRepository.save(user);
		
	}

	public void updateExerciseById(ExerciseDto exerciseDto, Long id) {
		Exercise existingExercise = getById(id);
		existingExercise.setName(exerciseDto.getName());
		existingExercise.setDescription(exerciseDto.getDescription());
		existingExercise.setSets(exerciseDto.getSets());
		existingExercise.setReps(exerciseDto.getReps());
		exerciseRepository.save(existingExercise);
	}

	public void deleteById(Long id) {
		exerciseRepository.deleteById(id);
		
	}

	

}
