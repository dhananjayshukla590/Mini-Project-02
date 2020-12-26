package com.shukla.repositories;


import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shukla.entities.StateEntity;
@Repository
public interface StateRepo extends JpaRepository<StateEntity, Serializable>{
	
	public List<StateEntity> findByCountryId(Integer countryId);

}
