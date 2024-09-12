package com.housing.back.repository;

import com.housing.back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//JpaRepository<무슨 엔티티?, 프라이머리키가 무엇?>
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUserId(String userId);

    boolean existsByUserId(String userId);

}
