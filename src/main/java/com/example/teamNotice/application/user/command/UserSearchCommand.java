package com.example.teamNotice.application.user.command;

import com.example.teamNotice.domain.user.model.UserKind;

/**
 * ユーザ更新コマンド
 */
public record UserSearchCommand(
        String name,
        UserKind userKind,
        Boolean isActive
) {
}
