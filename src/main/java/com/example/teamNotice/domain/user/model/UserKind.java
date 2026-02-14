package com.example.teamNotice.domain.user.model;

/**
 * ユーザ区分
 */
public enum UserKind {
    /** 管理者 */
    ADMIN(1),
    /** 一般 */
    NORMAL(2);

    private final int code;

    UserKind (int code) {
        this.code = code;
    }

    /**
     * コードから列挙型インスタンスを作成
     *
     * @param code ユーザ区分値
     * @return 対応するユーザ区分
     */
    public static UserKind of(int code) {
        for (UserKind kind : values()) {
            if (kind.code == code) {
                return kind;
            }
        }
        throw new IllegalArgumentException("Unknown UserKind code: " + code);
    }

    public int getCode() {
        return code;
    }
}
