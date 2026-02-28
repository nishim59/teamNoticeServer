package com.example.teamNotice.domain.user.model;

import java.time.LocalDateTime;

/**
 * ユーザドメインエンティティ
 */
public class User {
    /** ID */
    private final UserId id;
    /** 名前 */
    private String name;
    /** 有効フラグ（true:有効） */
    private boolean isActive;
    /** ユーザ区分 */
    private UserKind userKind;
    /** 作成日時 */
    private LocalDateTime createdAt;
    /** 更新日時 */
    private LocalDateTime updatedAt;
    /** 削除日時（null の場合、未削除） */
    private LocalDateTime deletedAt;

    /**
     * コンストラクタはpublicにしない
     */
    private User(
            UserId id,
            String name,
            boolean isActive,
            UserKind userKind,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.userKind = userKind;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * 新たなユーザエンティティを作成する。<br>
     * IDは自動振出、有効フラグは無効状態
     *
     * @param name 新規作成するユーザのユーザ名
     * @param userKind 新k塩飽精するユーザのユーザ区分
     * @return 作成されたユーザエンティティ
     */
    public static User create(String name, UserKind userKind) {
        return new User(
                UserId.create(),
                name,
                false,
                userKind,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    /**
     * updateする際にentity→domainに変換用
     *
     * @param id 更新対象のユーザid
     * @param name 更新するユーザの名前
     * @param userKind 更新するユーザの区分
     * @param updatedAt 更新日時
     * @return 更新されたユーザエンティティ
     */
    public static User update(
            UserId id,
            String name,
            boolean isActive,
            UserKind userKind,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new User(
                id,
                name,
                isActive,
                userKind,
                createdAt,
                updatedAt
        );
    }


    public UserId getId () {
        return id;
    }

    public String getName () {
        return name;
    }

    public boolean isActive () {
        return isActive;
    }

    public UserKind getUserKind () {
        return userKind;
    }

    public LocalDateTime getCreatedAt () {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt () {
        return updatedAt;
    }

    /**
     * 名前更新
     *
     * @param name 更新する名前
     * @throws IllegalArgumentException 空白orNULL
     */
    public void changeName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("User name must not be blank or null.");
        }
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 区分を変更
     *
     * @param userKind 変更する区分
     */
    public void changeKind (UserKind userKind) {
        this.userKind = userKind;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 有効化
     */
    public void activate() {
        if (this.isActive) {
            // すでに有効なら何もしない
            return;
        }
        this.isActive = true;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 無効化
     */
    public void deactivate() {
        if (!this.isActive) {
            // すでに無効なら何もしない
            return;
        }
        this.isActive = false;
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * 削除
     */
    public void delete() {
        // 削除済みなら何もしない
        if (this.deletedAt != null) {
            return;
        }
        this.deletedAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User other)) {
            return false;
        }
        return other.id != null && other.id != this.id;
    }

    @Override
    public int hashCode() {
        return (this.id != null) ? this.id.hashCode() : System.identityHashCode(this);
    }
}
