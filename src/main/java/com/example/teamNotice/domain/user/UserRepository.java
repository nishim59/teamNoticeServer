package com.example.teamNotice.domain.user;

import com.example.teamNotice.domain.user.model.User;
import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;

import java.util.List;
import java.util.Optional;

/**
 * ユーザ永続化
 */
public interface UserRepository {
    /**
     * 保存する。
     * - IDが存在していない場合には新規作成
     * - 存在していれば対象のデータを更新
     *
     * @param user 保存対象ユーザ
     */
    void save(User user);

    /**
     * 全取得
     *
     * @param name 名前検索条件（前後部分一致、NULLで条件に入れない）
     * @param kind 種別検索条件（NULLで条件に入れない）
     * @param isActive 有効フラグ検索条件（NULLで条件に入れない）
     * @return ユーザリスト
     */
    List<User> fetchAll(String name, UserKind kind, Boolean isActive);

    /**
     * ID検索
     *
     * @param id 検索対象ID
     * @return 取得できたユーザ。ID非存在でNULL
     */
    Optional<User> findById(UserId id);

    /**
     * ID存在確認
     *
     * @param id 確認対象ID
     * @return true：すでにIDが存在する
     */
    boolean exists(UserId id);

    /**
     * 楽観ロックを意識して更新
     *
     * @param user 更新情報
     * @return 更新可否。true：更新成功
     */
    boolean updateWithOptimisticLock(User user);
}
