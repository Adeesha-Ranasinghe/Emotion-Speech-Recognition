package com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SPEECHEMOTIONRECOGNITIONSYSTEM.Model.User;






public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);

}
