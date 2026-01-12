    package com.example.teamNotice;

    import com.example.teamNotice.application.user.UserApplicationService;
    import com.example.teamNotice.application.user.command.UserCreateCommand;
    import com.example.teamNotice.application.user.command.UserSearchCommand;
    import com.example.teamNotice.application.user.command.UserUpdateCommand;
    import com.example.teamNotice.domain.user.UserRepository;
    import com.example.teamNotice.domain.user.model.User;
    import com.example.teamNotice.domain.user.model.UserId;
    import com.example.teamNotice.domain.user.model.UserKind;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mock;
    import org.mockito.junit.jupiter.MockitoExtension;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;

    import java.util.List;
    import java.util.Optional;

    import static org.mockito.Mockito.when;

    @ExtendWith(MockitoExtension.class)
    public class UserApplicationServiceTests {
        private static final Logger log = LoggerFactory.getLogger(UserApplicationServiceTests.class);

        @Mock
        UserRepository repository;

        @InjectMocks
        UserApplicationService service;

        // ユーザー新規作成コマンド
        private static final UserCreateCommand createCommand = new UserCreateCommand("test",1);

        @Test
        void createTest(){
            UserId userId = service.create(createCommand);

            log.info(String.valueOf(userId));
        }

        // ユーザー更新
        @Test
        void updateTest(){
            // 事前にユーザー作成
            User createdUser = User.create(
                    createCommand.name(),
                    UserKind.of(createCommand.userKindCode())
            );
            UserId userID = createdUser.getId();
            log.info("---------更新前----------");
            log.info(String.valueOf(userID));
            log.info(String.valueOf(createdUser.getName()));
            log.info(String.valueOf(createdUser.getUserKind()));
            log.info(String.valueOf(createdUser.getUpdatedAt()));

            // service.updateを呼び出す際に、作成されたidに紐づくユーザーを返却させる
            when(repository.findById(userID)).thenReturn(Optional.of(createdUser));
            // service.updateを呼び出す際に、更新可を返却させる
            when(repository.updateWithOptimisticLock(createdUser)).thenReturn(true);

            // ユーザー情報を更新
            UserUpdateCommand updateCommand = new UserUpdateCommand(userID,"updTest", UserKind.of(2));

            service.update(updateCommand);

            log.info("---------更新後----------");
            log.info(String.valueOf(updateCommand.id()));
            log.info(String.valueOf(updateCommand.name()));
            log.info(String.valueOf(updateCommand.userKind()));
            log.info(String.valueOf(createdUser.getUpdatedAt()));
        }

        // ユーザー削除
        @Test
        void deleteTest(){
            // 事前にユーザー作成
            User createdUser = User.create(
                    createCommand.name(),
                    UserKind.of(createCommand.userKindCode())
            );
            UserId userID = createdUser.getId();
            log.info("---------削除前----------");
            log.info(String.valueOf(userID));
            log.info(String.valueOf(createdUser.getName()));
            log.info(String.valueOf(createdUser.getUserKind()));
            log.info(String.valueOf(createdUser.getUpdatedAt()));

            // service.updateを呼び出す際に、作成されたidに紐づくユーザーを返却させる
            when(repository.findById(userID)).thenReturn(Optional.of(createdUser));
            // service.updateを呼び出す際に、更新可を返却させる
            when(repository.updateWithOptimisticLock(createdUser)).thenReturn(true);

            // ユーザー情報を削除
            service.delete(userID);

            log.info("---------削除後----------");
            log.info(String.valueOf(userID));
            log.info(String.valueOf(createdUser.getName()));
            log.info(String.valueOf(createdUser.getUserKind()));
            log.info(String.valueOf(createdUser.getUpdatedAt()));

            // 削除したユーザーを削除しにいく
            service.delete(userID);
            log.info("---------再削除後----------");
            log.info(String.valueOf(userID));
            log.info(String.valueOf(createdUser.getName()));
            log.info(String.valueOf(createdUser.getUserKind()));
            // 更新されていなければOK
            log.info(String.valueOf(createdUser.getUpdatedAt()));
        }

        // ユーザー検索
//        @Test
//        void searchTest(){
//            // 事前にユーザー作成
//            User createdUser = User.create(
//                    createCommand.name(),
//                    UserKind.of(createCommand.userKindCode())
//            );
//
//            // 検索コマンドのパラメータ設定
//            UserSearchCommand searchCommand = new UserSearchCommand("test",UserKind.ADMIN,true);
//
//            // 検索用の返却地を事前に設定
//            when(repository.fetchAll(searchCommand.name(), searchCommand.userKind(), searchCommand.isActive())).thenReturn((List<User>) createdUser);
//
//            // 検索
//            service.search(searchCommand);
//        }

}
