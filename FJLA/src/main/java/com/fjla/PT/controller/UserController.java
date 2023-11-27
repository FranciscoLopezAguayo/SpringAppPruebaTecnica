package com.fjla.PT.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fjla.PT.config.ApplicationConfig;
import com.fjla.PT.model.ResponseMessage;
import com.fjla.PT.model.Users;
import com.fjla.PT.model.ValidadorEmail;
import com.fjla.PT.repository.IUserRepository;
import com.fjla.PT.service.IUserService; 


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	IUserRepository UserRepository;
	
	@GetMapping("/Demo")
	public String welcome() {
		
		String ER = ApplicationConfig.REGEXSTR();
		return "welcome from public endpoint--" + ER;
		
	}


 	@Autowired
    private PasswordEncoder bcrypt;
    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Users user) {
       user.setPassword(bcrypt.encode(user.getPassword()));
       return ResponseEntity.ok(userService.save(user));
    }

	
	
	
	@GetMapping("/Users")
	public ResponseEntity<List<Users>> getAllUsers(@RequestParam(required = false) String email) {
		try {
			List<Users> Users = new ArrayList<Users>();

			if (email == null)
				UserRepository.findAll().forEach(Users::add);
			else
				//UserRepository.findByEmail(email).forEach(Users::add);
				UserRepository.findByEmail(email);

			if (Users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(Users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/Users/{id}")
	public ResponseEntity<Users> getUserById(@PathVariable("id") UUID id) {
		Optional<Users> UserData = UserRepository.findById(id);

		if (UserData.isPresent()) {
			return new ResponseEntity<>(UserData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	

	@PostMapping("/Users")
	public ResponseEntity<Object> createUser(@RequestBody Users User) {
		try {
			
			String Email = User.getEmail();
	        if (ValidadorEmail.validteEmail(Email)) {
	            System.out.println("El correo es válido.");
	        } else {
	        	return new ResponseEntity<>(new ResponseMessage("El correo no es válido."),null, HttpStatus.BAD_REQUEST);
	        }
			
			
			Users _User = UserRepository
					.save(new Users(User.getName(), User.getEmail(), User.getPassword(), User.getPhones()));
					//.save(new User(User.getName(), User.getEmail(), User.getPassword()));
			return new ResponseEntity<>(_User, HttpStatus.CREATED);
				
		} catch (DataIntegrityViolationException  e) {
			
			System.out.println("*****************DataIntegrityViolationException *********************");
                //e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage("El correo ya se encuentra registrado"),null, HttpStatus.BAD_REQUEST);
		}catch (ConstraintViolationException  e) {
			System.out.println("*****************ConstraintViolationException *********************");
               // e.printStackTrace();
			return new ResponseEntity<>(new ResponseMessage("El Telefono ya se encuentra registrado"),null, HttpStatus.BAD_REQUEST);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("",null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	
	
	@PutMapping("/Users/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") UUID id, @RequestBody Users User) {
		Optional<Users> UserData = UserRepository.findById(id);

		if (UserData.isPresent()) {
			Users _User = UserData.get();
			_User.setName(User.getName());
			_User.setEmail(User.getEmail());
			_User.setPassword(User.getPassword());
			return new ResponseEntity<>(UserRepository.save(_User), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/Users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") UUID id) {
		try {
			UserRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			UserRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	
	
}
