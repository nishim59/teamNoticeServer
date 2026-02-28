package com.example.teamNotice.infrastructure.db.user;

import com.example.teamNotice.infrastructure.db.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

}
