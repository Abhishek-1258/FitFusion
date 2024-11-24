package com.CN.FitFusion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.CN.FitFusion.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	
	Optional<User> findByEmail(String username);

}
