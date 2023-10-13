package com.ThankSen.BaiTap2.Repository;

import com.ThankSen.BaiTap2.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    UserEntity findByEmail(String email);

}
