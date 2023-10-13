package com.ThankSen.BaiTap2.Service;

import com.ThankSen.BaiTap2.Entity.RoleEntity;
import com.ThankSen.BaiTap2.Payload.Request.RoleRequest;
import com.ThankSen.BaiTap2.Payload.RoleDto;
import com.ThankSen.BaiTap2.Repository.RoleRepository;
import com.ThankSen.BaiTap2.Service.InterfaceService.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements RoleServiceImp {
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public boolean createRoles(RoleRequest roleRequest) {
        try {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(roleRequest.getName());
            roleEntity.setDescription(roleRequest.getDescription());

            roleRepository.save(roleEntity);

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<RoleDto> getAllRole() {
        List<RoleEntity> roleEntityList = roleRepository.findAll();

        List<RoleDto> roleDtoList = new ArrayList<>();

        for (RoleEntity roleEntity : roleEntityList
        ) {
            RoleDto roleDto = new RoleDto(roleEntity.getName(), roleEntity.getDescription(), roleEntity.getId());
            roleDtoList.add(roleDto);
        }

        return roleDtoList;
    }

    @Override
    public boolean updateRole(int id, RoleRequest roleRequest) {
        RoleEntity roleEntity = roleRepository.findById(id);

        if (roleEntity != null) {
            roleEntity.setName(roleRequest.getName());
            roleEntity.setDescription(roleRequest.getDescription());
            roleRepository.save(roleEntity);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteRole(int id) {
        try {
            roleRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public RoleDto getDetailRole(int id) {
        try {
            RoleEntity roleEntity = roleRepository.findById(id);
            RoleDto roleDto = new RoleDto(roleEntity.getName(), roleEntity.getDescription(), roleEntity.getId());
            return roleDto;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
