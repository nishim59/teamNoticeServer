package com.example.teamNotice.infrastructure.db.user;

import com.example.teamNotice.domain.user.UserRepository;
import com.example.teamNotice.domain.user.model.User;
import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;
import com.example.teamNotice.infrastructure.db.user.entity.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDBRepository implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserDBRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * 保存する。
     * - IDが存在していない場合には新規作成
     * - 存在していれば対象のデータを更新
     *
     * @param user 保存対象ユーザ
     */
    @Override
    public void save(User user) {

        UserEntity entity = new UserEntity(
                extractUuid(user),
                user.getName(),
                user.isActive(),
                user.getUserKind(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );

        userJpaRepository.save(entity);

    }

    /**
     * 全取得
     *
     * @param name     名前検索条件（前後部分一致、NULLで条件に入れない）
     * @param kind     種別検索条件（NULLで条件に入れない）
     * @param isActive 有効フラグ検索条件（NULLで条件に入れない）
     * @return ユーザリスト
     */
    @Override
    public List<User> fetchAll(String name, UserKind kind, Boolean isActive) {

        return userJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    /**
     * ID検索
     *
     * @param id 検索対象ID
     * @return 取得できたユーザ。ID非存在でNULL
     */
    @Override
    public Optional<User> findById(UserId id) {

        return userJpaRepository
                .findById(id.value()).map(this::toDomain);
    }

    /**
     * ID存在確認
     *
     * @param id 確認対象ID
     * @return true：すでにIDが存在する
     */
    @Override
    public boolean exists(UserId id) {
        return false;
    }

    /**
     * 楽観ロックを意識して更新
     *
     * @param user 更新情報
     * @return 更新可否。true：更新成功
     */
    @Override
    public boolean updateWithOptimisticLock(User user) {
        return false;
    }

    /**
     * 更新する
     *
     * @param user 更新情報
     */
    @Override
    @Transactional
    public void update(User user) {

        UserEntity entity = userJpaRepository
                .findById(user.getId().value())
                .orElseThrow(() -> new RuntimeException("not found"));

        entity.setId(extractUuid(user));
        entity.setName(user.getName());
        entity.setActive(user.isActive());
        entity.setUserKind(user.getUserKind());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        entity.setDeletedAt(user.getDeletedAt());
    }

    /**
     * DBから受け取ったエンティティをドメインエンティティに変換
     *
     * @param entity 変換対象エンティティ
     * @return 更新されたユーザーエンティティ
     */
    private User toDomain(UserEntity entity) {

        return User.update(
                entity.getId() == null ? null : new UserId(entity.getId()),
                entity.getName(),
                entity.isActive(),
                entity.getUserKind(),
                entity.getCreatedAt(),
                entity.getUpdatedAt(),
                entity.getDeletedAt()
        );
    }

    /**
     * UserId型をUUIDに変換
     *
     * @param user UserId型のユーザーID
     * @return UUID UUID型のユーザID
     */
    private UUID extractUuid(User user) {
        if (user.getId() == null) {
            return null;
        }
        return user.getId().value();
    }

}
