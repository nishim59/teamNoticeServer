package com.example.teamNotice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

@SpringBootTest
public class DBConnectionTests {

    @Autowired
    DataSource dataSource;

    @Test
    void dbConnectionTest() throws Exception {
        try (var conn = dataSource.getConnection()) {
            System.out.println("DB接続OK: " + conn.getMetaData().getURL());
        }
    }

}
