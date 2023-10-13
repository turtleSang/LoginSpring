package com.ThankSen.BaiTap2.Repository;

import com.ThankSen.BaiTap2.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findById(int id);
}
