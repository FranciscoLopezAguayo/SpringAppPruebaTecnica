package com.fjla.PT.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.fjla.PT.model.Phones;
import com.fjla.PT.model.Users;

public interface IUserRepository extends JpaRepository<Users, UUID> {
	
	//List<User> findByName(String name);
	
	//List<User> findByEmail(String email);

	public Users findByEmail(String email);
	
	//Optional<User> findByEmail(String email);
	

}
