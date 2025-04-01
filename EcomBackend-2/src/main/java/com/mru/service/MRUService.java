package com.mru.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mru.entity.Laptops;
import com.mru.entity.Mobiles;
import com.mru.entity.User;
import com.mru.entity.Watches;
import com.mru.repo.LaptopsRepo;
import com.mru.repo.MobilesRepo;
import com.mru.repo.UserRepo;
import com.mru.repo.WatchesRepo;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;



@Service
public class MRUService {
	@Autowired
	private LaptopsRepo repo1;
	@Autowired
	private MobilesRepo repo2;
	@Autowired
	private WatchesRepo repo3;
	@Autowired
	private UserRepo userRepo;
	
	@Value("${aws.s3.bucket.name}")
	private String bucketName;
	    
	    
	@Value("${aws.accessKeyId}")
	private String accessKeyId;

	@Value("${aws.secretAccessKey}")
	private String secretAccessKey;
	
	
	 //getting reference of s3 buckets
	 private final S3Client s3Client = S3Client.builder()
	            .region(Region.EU_NORTH_1)
	            .credentialsProvider(StaticCredentialsProvider.create(
	                AwsBasicCredentials.create("AKIAXBZV5PTYNBDRKEEM", "s2zfdxpt2Oz3YXLT/6N4T8wgvMwi02xZFzPApSw1")
	 )).build();
	 
	 
	 
	 public Laptops saveLaptop(String pname, int pcost, int pqty, MultipartFile file) throws IOException {
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        try {
	            // Upload to S3
	            s3Client.putObject(
	                PutObjectRequest.builder()
	                    .bucket(bucketName)
	                    .key(fileName)
	                    
	                    .contentType("image/jpeg")
	                    .build(),
	                RequestBody.fromBytes(file.getBytes())
	            );
	        } catch (Exception e) {
	            throw new RuntimeException("Error uploading file to S3: " + e.getMessage());
	        }

	        //String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.US_EAST_1.id(), fileName);

	        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.EU_NORTH_1.id(), fileName);
	        System.out.println("File uploaded successfully. File URL: " + fileUrl);

	        
	        try {
	            // Save to database
	            Laptops laptop = new Laptops();
	            laptop.setPname(pname);
	            laptop.setPcost(pcost);
	            laptop.setPimage(fileUrl);
	            laptop.setPqty(pqty);
	            System.out.println("Saving Laptop: pname=" + pname + ", pcost=" + pcost + ", pimage=" + fileUrl);

	            return repo1.save(laptop);
	        } catch (Exception e) {
	            throw new RuntimeException("Error saving laptop to database: " + e.getMessage());
	        }
	    }
	
	
	
	 public Mobiles saveMobiles(String pname, int pcost, int pqty, MultipartFile file) throws IOException {
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        try {
	            // Upload to S3
	            s3Client.putObject(
	                PutObjectRequest.builder()
	                    .bucket(bucketName)
	                    .key(fileName)
	                    
	                    .contentType("image/jpeg")
	                    .build(),
	                RequestBody.fromBytes(file.getBytes())
	            );
	        } catch (Exception e) {
	            throw new RuntimeException("Error uploading file to S3: " + e.getMessage());
	        }

	        //String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.US_EAST_1.id(), fileName);

	        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.EU_NORTH_1.id(), fileName);
	        System.out.println("File uploaded successfully. File URL: " + fileUrl);

	        
	        try {
	            // Save to database
	            Mobiles mobile = new Mobiles();
	            mobile.setPname(pname);
	            mobile.setPcost(pcost);
	            mobile.setPimage(fileUrl);
	            mobile.setPqty(pqty);
	            System.out.println("Saving Mobile: pname=" + pname + ", pcost=" + pcost + ", pimage=" + fileUrl);

	            return repo2.save(mobile);
	        } catch (Exception e) {
	            throw new RuntimeException("Error saving mobile to database: " + e.getMessage());
	        }
	    }
	
	
	 public Watches saveWatches(String pname, int pcost, int pqty, MultipartFile file) throws IOException {
	        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	        try {
	            // Upload to S3
	            s3Client.putObject(
	                PutObjectRequest.builder()
	                    .bucket(bucketName)
	                    .key(fileName)
	                    
	                    .contentType("image/jpeg")
	                    .build(),
	                RequestBody.fromBytes(file.getBytes())
	            );
	        } catch (Exception e) {
	            throw new RuntimeException("Error uploading file to S3: " + e.getMessage());
	        }

	        //String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.US_EAST_1.id(), fileName);

	        String fileUrl = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Region.EU_NORTH_1.id(), fileName);
	        System.out.println("File uploaded successfully. File URL: " + fileUrl);

	        
	        try {
	            // Save to database
	            Watches watch = new Watches();
	            watch.setPname(pname);
	            watch.setPcost(pcost);
	            watch.setPimage(fileUrl);
	            watch.setPqty(pqty);
	            System.out.println("Saving Watch: pname=" + pname + ", pcost=" + pcost + ", pimage=" + fileUrl);

	            return repo3.save(watch);
	        } catch (Exception e) {
	            throw new RuntimeException("Error saving watch to database: " + e.getMessage());
	        }
	    }
	
	
	
	public List<Laptops> getAllLaptops(){
		return repo1.findAll();
	}
	public List<Mobiles> getAllMobiles(){
		return repo2.findAll();
	}
	public List<Watches> getAllWatches(){
		return repo3.findAll();
	}
	
	 // Register User
    public String registerUser(User user) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        userRepo.save(user);
        return "User registered successfully!";
    }

    // Login User
    public String loginUser(String username, String password) {
     	Optional<User> existingUser = userRepo.findByUsername(username);
        if (existingUser.isPresent() && existingUser.get().getPassword().equals(password)) {
            return "Login successful!";
        }
        return "Invalid username or password";
    }
	
}
