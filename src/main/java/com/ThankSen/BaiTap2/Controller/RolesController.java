package com.ThankSen.BaiTap2.Controller;

import com.ThankSen.BaiTap2.Payload.CreateDto;
import com.ThankSen.BaiTap2.Payload.Request.RoleRequest;
import com.ThankSen.BaiTap2.Payload.RoleDto;
import com.ThankSen.BaiTap2.Service.InterfaceService.RoleServiceImp;
import com.ThankSen.BaiTap2.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RolesController {
    private RoleServiceImp roleServiceImp;

    @Autowired
    public RolesController(RoleServiceImp roleServiceImp){
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping("/getAll")
    public  ResponseEntity<?> getAllRoles(){
        List<RoleDto> roleDtoList = roleServiceImp.getAllRole();
        return new ResponseEntity<>(roleDtoList, HttpStatus.OK);
    }

    @GetMapping("/getDetail")
    public  ResponseEntity<?> getDetailRoles(@RequestParam int id){

        RoleDto roleDto = roleServiceImp.getDetailRole(id);


        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRole(@RequestBody RoleRequest roleRequest){

        boolean check = roleServiceImp.createRoles(roleRequest);

        if (check){
            CreateDto createDto = new CreateDto(true, "Create Successful",null);
            return new ResponseEntity<>(createDto, HttpStatus.CREATED);
        }else {
            CreateDto createDto = new CreateDto(false, "Create Fail", null);
            return new ResponseEntity<>(createDto, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRole(@RequestParam int id, @RequestBody RoleRequest roleRequest){

        boolean check = roleServiceImp.updateRole(id, roleRequest);

        if (check){
            CreateDto createDto = new CreateDto(true, "Update Successful", roleRequest);
            return new ResponseEntity<>(createDto, HttpStatus.ACCEPTED);
        }else {
            CreateDto createDto = new CreateDto(false, "Update fail", null);
            return new ResponseEntity<>(createDto, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRole(@RequestParam int id){

        boolean check = roleServiceImp.deleteRole(id);
        if (check){
            CreateDto createDto = new CreateDto(true, "Delete Successful", null);
            return new ResponseEntity<>(createDto, HttpStatus.ACCEPTED);
        }else {
            CreateDto createDto = new CreateDto(false, "Delete fail", null);
            return new ResponseEntity<>(createDto, HttpStatus.BAD_REQUEST);
        }
    }


}
