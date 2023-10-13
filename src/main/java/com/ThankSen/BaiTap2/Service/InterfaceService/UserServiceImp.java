package com.ThankSen.BaiTap2.Service.InterfaceService;

import com.ThankSen.BaiTap2.Entity.UserEntity;
import com.ThankSen.BaiTap2.Payload.Request.UserRequest;
import com.ThankSen.BaiTap2.Payload.UserDto;

import java.util.List;

public interface UserServiceImp {

    UserDto getDetailUser(String email);
    List<UserDto> getAllUser();
    boolean createUser(UserRequest userRequest);
    boolean updateUser(UserRequest userRequest);
    boolean deleteUser(String email);
    String checkLogin(String email, String password);
    boolean checkToken(String token);
}
