package com.fjla.PT.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fjla.PT.model.Phones;
//import com.fjla.PT.model.User;

public interface PhonesRepository extends JpaRepository<Phones, UUID> {
	
	List<Phones> findBynumber(String number);
	
	List<Phones> findBycontrycode(String contrycode);
}
