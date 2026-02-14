package com.example.teamNotice.infrastructure.db.user.entity;

import com.example.teamNotice.domain.user.model.UserKind;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

/*
テーブル定義と一致するエンティティ
 */
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    /* ID */
    private UUID id;
    /* 名前 */
    private String name;
    /* 有効フラグ（true:有効） */
    private boolean isActive;
    /* ユーザ区分 */
    private UserKind userKind;
    /* 作成日時 */
    private LocalDateTime createdAt;
    /* 更新日時 */
    private LocalDateTime updatedAt;

    public UserEntity() {
    }

    public UserEntity(UUID id, String name, boolean isActive, UserKind userKind, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.userKind = userKind;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserKind getUserKind() {
        return userKind;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
