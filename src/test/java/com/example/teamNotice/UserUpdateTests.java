package com.example.teamNotice;

import com.example.teamNotice.application.user.UserApplicationService;
import com.example.teamNotice.application.user.command.UserUpdateCommand;
import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.UUID;

@SpringBootTest
public class UserUpdateTests {
    private static final Logger log = LoggerFactory.getLogger(UserApplicationServiceTests.class);

    @Autowired
    UserApplicationService service;

    @Autowired
    DataSource dataSource;

    // ユーザー新規作成コマンド
    static UUID fixedUuid = UUID.fromString("e031eecb-1531-4905-8ed5-8329aa531980");
    static UserId userId = new UserId(fixedUuid);
    private static final UserUpdateCommand updateCommand = new UserUpdateCommand(userId, "test_n3", UserKind.of(2));

    @Test
    void updateTest() {
        service.update(updateCommand);

    }

}
