package com.ThankSen.BaiTap2.Service;

import com.ThankSen.BaiTap2.Entity.RoleEntity;
import com.ThankSen.BaiTap2.Entity.UserEntity;
import com.ThankSen.BaiTap2.JwtUnit.DataToken;
import com.ThankSen.BaiTap2.JwtUnit.JwtHelper;
import com.ThankSen.BaiTap2.Payload.Request.UserRequest;
import com.ThankSen.BaiTap2.Payload.UserDto;
import com.ThankSen.BaiTap2.Repository.UserRepository;
import com.ThankSen.BaiTap2.Service.InterfaceService.StorageServiceImp;
import com.ThankSen.BaiTap2.Service.InterfaceService.UserServiceImp;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp{

    private JwtHelper jwtHelper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private StorageServiceImp storageServiceImp;
    private Gson gson = new Gson();


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtHelper jwtHelper, StorageServiceImp storageServiceImp) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.jwtHelper = jwtHelper;
        this.storageServiceImp = storageServiceImp;
    }

    @Override
    public UserDto getDetailUser( String email) {
        try {
            UserEntity userEntity = userRepository.findByEmail(email);
            UserDto userDto = new UserDto(userEntity.getEmail(), userEntity.getFullname(), userEntity.getPassword(), userEntity.getAvatar(),
                    userEntity.getPhone(), userEntity.getAddress(), userEntity.getWebsite(), userEntity.getFacebook(), userEntity.getRoleEntity().getName());
            return userDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<UserDto> getAllUser() {
        List<UserEntity> userEntityList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity userEntity: userEntityList
             ) {
            UserDto userDto = new UserDto(userEntity.getEmail(), userEntity.getFullname(), userEntity.getPassword(),
                    userEntity.getAvatar(),userEntity.getPhone(),userEntity.getAddress(),userEntity.getWebsite(), userEntity.getFacebook(),
                    userEntity.getRoleEntity().getName());
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    @Override
    public boolean createUser(UserRequest userRequest) {

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(userRequest.getEmail());
            userEntity.setFullname(userRequest.getFullname());
            userEntity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            userEntity.setAvatar(userRequest.getAvatar());
            userEntity.setPhone(userRequest.getPhone());
            userEntity.setAddress(userRequest.getAddress());
            userEntity.setFacebook(userRequest.getFacebook());
            userEntity.setWebsite(userRequest.getWebsite());
            userEntity.setFacebook(userEntity.getFacebook());

            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setId(userRequest.getRole_id());



            userEntity.setRoleEntity(roleEntity);

            userRepository.save(userEntity);
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateUser(UserRequest userRequest) {
        UserEntity userEntity = userRepository.findByEmail(userRequest.getEmail());
        if (userEntity != null){
            userEntity.setFullname(userRequest.getFullname());
            userEntity.setPassword(userRequest.getPassword());
            userEntity.setAvatar(userRequest.getAvatar());
            userEntity.setPhone(userRequest.getPhone());
            userEntity.setAddress(userRequest.getAddress());
            userEntity.setFacebook(userRequest.getFacebook());
            userEntity.setWebsite(userRequest.getWebsite());
            userEntity.setFacebook(userEntity.getFacebook());

            userRepository.save(userEntity);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null){
            userRepository.delete(userEntity);
            return true;
        }

        return false;
    }

    @Override
    public String checkLogin(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null && passwordEncoder.matches(password, userEntity.getPassword())){
            DataToken dataToken = new DataToken();
            dataToken.setEmail(email);
            dataToken.setRole_name(userEntity.getRoleEntity().getName());
            String jsonData = gson.toJson(dataToken);
            String token  = jwtHelper.generateJwt(jsonData);
            return token;
        }

        return null;
    }

    @Override
    public boolean checkToken(String token) {
        Authentication authentication = jwtHelper.decodeJwt(token);
        if (authentication == null){
            return false;
        }
        return true;
    }


}
