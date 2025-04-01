package com.mru.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mru.entity.CustomerOrder;
import com.mru.entity.Laptops;
import com.mru.entity.Mobiles;
import com.mru.entity.User;
import com.mru.entity.Watches;
import com.mru.repo.OrderRepository;
import com.mru.repo.UserRepo;
import com.mru.service.MRUService;
import com.mru.util.JwtUtil;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class MRUController {
	@Autowired
	private MRUService service;
	
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepo userRepository;
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginData){
		//read username & password from loginData
		String username = loginData.get("username");
		String password = loginData.get("password");
		//compare react username with database
		Optional<User> user = userRepository.findByUsername(username);
		if(user.isPresent() && user.get().getPassword().equals(password)) {
			Map<String, String> response = new HashMap<>();
			String token = jwtUtil.generateToken(username);
			response.put("login", "success");
			response.put("token", token);
			response.put("role", user.get().getRole());
			return ResponseEntity.ok(response);
		}else {
			Map<String, String> response1 = new HashMap<>();
			response1.put("login", "fail");
			return ResponseEntity.status(401).body(response1);
		}
	}
	
	
	
	@GetMapping("/user/laptops")
	public List<Laptops> getLaptops(){
		return service.getAllLaptops();
	}
	@GetMapping("/user/mobiles")
	public List<Mobiles> getMobiles(){
		return service.getAllMobiles();
	}
	@GetMapping("/user/watches")
	public List<Watches> getWatches(){
		return service.getAllWatches();
	}
	@Autowired
    private OrderRepository orderRepo;
    @PostMapping("/save-order")
    public CustomerOrder saveOrder(@RequestBody CustomerOrder order) {
        return orderRepo.save(order);
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String response = service.registerUser(user);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/admin/upload")
    public String uploadImages() {
    	return "images will upload soon...";
    }
    
    
    @PostMapping("/admin/upload/laptops")
	public ResponseEntity<?> uploadLaptops(@RequestParam String pname,
								@RequestParam int pqty,
								@RequestParam int pcost,
								@RequestParam MultipartFile file) {
		 if (pname == null || pname.isEmpty() || pcost <= 0 || file == null || file.isEmpty() || pqty<=0) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input parameters");
         }else {
        	 Laptops savedLaptop = null;
			try {
				savedLaptop = service.saveLaptop(pname, pcost, pqty, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return ResponseEntity.ok(savedLaptop);
         }
	}
	
    @PostMapping("/admin/upload/mobiles")
	public ResponseEntity<?> uploadMobiles(@RequestParam String pname,
								@RequestParam int pqty,
								@RequestParam int pcost,
								@RequestParam MultipartFile file) {
		 if (pname == null || pname.isEmpty() || pcost <= 0 || file == null || file.isEmpty() || pqty<=0) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input parameters");
         }else {
        	 Mobiles savedMobile = null;
			try {
				savedMobile = service.saveMobiles(pname, pcost, pqty, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return ResponseEntity.ok(savedMobile);
         }
	}
	
    @PostMapping("/admin/upload/watches")
	public ResponseEntity<?> uploadWatches(@RequestParam String pname,
								@RequestParam int pqty,
								@RequestParam int pcost,
								@RequestParam MultipartFile file) {
		 if (pname == null || pname.isEmpty() || pcost <= 0 || file == null || file.isEmpty() || pqty<=0) {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input parameters");
         }else {
        	 Watches savedWatch = null;
			try {
				savedWatch = service.saveWatches(pname, pcost, pqty, file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return ResponseEntity.ok(savedWatch);
         }
	}
    
}
