package com.ThankSen.BaiTap2.Service.InterfaceService;

import com.ThankSen.BaiTap2.Payload.Request.RoleRequest;
import com.ThankSen.BaiTap2.Payload.RoleDto;

import java.util.List;

public interface RoleServiceImp {
    boolean createRoles(RoleRequest roleRequest);

    List<RoleDto> getAllRole();

    boolean updateRole(int id, RoleRequest roleRequest);

    boolean deleteRole(int id);

    RoleDto getDetailRole(int id);
}
