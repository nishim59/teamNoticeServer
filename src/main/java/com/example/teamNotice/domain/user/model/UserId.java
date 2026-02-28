package com.example.teamNotice.domain.user.model;

import java.util.UUID;

/**
 * ユーザID（値）
 */
public record UserId(
        UUID value
) {
    /**
     * ランダムなUUIDによるUserIDを作成する
     * @return 新たに作成されたUserID
     */
    public static UserId create() {
            return new UserId(UUID.randomUUID());
    }

    /**
     * 文字列から作成する
     * @param id 文字列のUUID
     * @return 作成されたUserID
     */
    public static UserId from(String id) {
        return new UserId(UUID.randomUUID());
    }

}