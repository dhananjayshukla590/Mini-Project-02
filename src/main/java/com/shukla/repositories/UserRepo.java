package com.shukla.repositories;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shukla.entities.UserEntity;

@Repository
public interface UserRepo extends JpaRepository<UserEntity,Serializable>{

	public UserEntity findByuserEmail(String emailId);
	
	public UserEntity findByUserEmailAndPassword(String emailId,String password);

}
