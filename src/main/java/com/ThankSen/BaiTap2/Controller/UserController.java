package com.ThankSen.BaiTap2.Controller;

import com.ThankSen.BaiTap2.Payload.CreateDto;
import com.ThankSen.BaiTap2.Payload.Error;
import com.ThankSen.BaiTap2.Payload.LoginDto;
import com.ThankSen.BaiTap2.Payload.Request.UserRequest;
import com.ThankSen.BaiTap2.Payload.UserDto;
import com.ThankSen.BaiTap2.Service.InterfaceService.StorageServiceImp;
import com.ThankSen.BaiTap2.Service.InterfaceService.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    private UserServiceImp userServiceImp;
    private StorageServiceImp storageServiceImp;

    @Autowired
    public UserController(UserServiceImp userServiceImp, StorageServiceImp storageServiceImp) {
        this.userServiceImp = userServiceImp;
        this.storageServiceImp = storageServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        boolean check = userServiceImp.createUser(userRequest);

        if (check) {
            return new ResponseEntity<>(new CreateDto(true, "Create Successful", null), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CreateDto(false, "Create fail", null), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        List<UserDto> listUser = userServiceImp.getAllUser();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getDetailUser(@PathVariable String email){
        UserDto userDto = userServiceImp.getDetailUser(email);
        if (userDto != null){
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(Error.NOT_FOUND, HttpStatus.NOT_FOUND);
    }

    @PutMapping("")
    public ResponseEntity<?> updateUser(@RequestBody UserRequest userRequest){

        boolean check = userServiceImp.updateUser(userRequest);

        if (check) {
            return new ResponseEntity<>(new CreateDto(true, "update Successful", null), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CreateDto(false, "update fail", null), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(@RequestParam String email){
        boolean check = userServiceImp.deleteUser(email);
        if (check) {
            return new ResponseEntity<>(new CreateDto(true, "update Successful", null), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new CreateDto(false, "update fail", null), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password){
        String token = userServiceImp.checkLogin(email, password);
        if (token != null){
            return new ResponseEntity<>(new LoginDto(true,token, "login successful"), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new LoginDto(false, "", "Wrong email or password"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/check")
    public ResponseEntity<?> checkToken(@RequestHeader String token){
        boolean check =userServiceImp.checkToken(token);
        if (check){
            return new ResponseEntity<>(new LoginDto(true,"", "Accepted"), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(new LoginDto(false,"", "Accepted"), HttpStatus.BAD_REQUEST );
    }

}
