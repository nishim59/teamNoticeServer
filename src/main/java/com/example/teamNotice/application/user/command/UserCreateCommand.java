package com.example.teamNotice.application.user.command;

/**
 * ユーザ作成コマンド
 */
public record UserCreateCommand(
        String name,
        int userKindCode
) {
}
