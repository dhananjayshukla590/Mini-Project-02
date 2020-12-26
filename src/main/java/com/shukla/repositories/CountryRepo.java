package com.shukla.repositories;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shukla.entities.CountryEntity;
@Repository
public interface CountryRepo extends JpaRepository<CountryEntity, Serializable> {

}
