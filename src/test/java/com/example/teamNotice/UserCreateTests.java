package com.example.teamNotice;

import com.example.teamNotice.application.user.UserApplicationService;
import com.example.teamNotice.application.user.command.UserCreateCommand;
import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class UserCreateTests {
    private static final Logger log = LoggerFactory.getLogger(UserApplicationServiceTests.class);

    @Autowired
    UserApplicationService service;

    @Autowired
    DataSource dataSource;

    // ユーザー新規作成コマンド
    private static final UserCreateCommand createCommand = new UserCreateCommand("test_n2", 2);

    @Test
    void createTest() {
        UserId userId = service.create(createCommand);

        log.info(String.valueOf(userId));
    }

}
