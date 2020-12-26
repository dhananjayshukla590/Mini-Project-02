package com.shukla.repositories;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shukla.entities.CityEntity;
@Repository
public interface CityRepo extends JpaRepository<CityEntity, Serializable>{
	
	public List<CityEntity> findByStateId(Integer stateId);

}
