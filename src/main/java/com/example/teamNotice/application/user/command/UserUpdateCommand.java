package com.example.teamNotice.application.user.command;

import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;

/**
 * ユーザ更新コマンド
 */
public record UserUpdateCommand(
        UserId id,
        String name,
        UserKind userKind
) {
}
