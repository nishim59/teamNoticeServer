package com.example.teamNotice.application.user;

import com.example.teamNotice.application.user.command.UserCreateCommand;
import com.example.teamNotice.application.user.command.UserSearchCommand;
import com.example.teamNotice.application.user.command.UserUpdateCommand;
import com.example.teamNotice.domain.user.UserRepository;
import com.example.teamNotice.domain.user.model.User;
import com.example.teamNotice.domain.user.model.UserId;
import com.example.teamNotice.domain.user.model.UserKind;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ユーザアプリケーション
 */
@Service
public class UserApplicationService {

    private final UserRepository userRepository;

    @Autowired
    public UserApplicationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 新たにユーザ情報を作成する。
     *
     * @param command 作成コマンド
     * @return 作成されたユーザのID
     */
    public UserId create(UserCreateCommand command){
        User createdUser = User.create(
                command.name(),
                UserKind.of(command.userKindCode())
        );
        userRepository.save(createdUser);
        return createdUser.getId();
    }

    /**
     * ユーザ情報を更新する
     *
     * @param command 更新コマンド
     */
    @Transactional
    public void update(UserUpdateCommand command){
        // 更新対象ユーザを取得
        Optional<User> target = userRepository.findById(command.id());
        // 取得できなければエラー
        User user = target.orElseThrow(() -> new RuntimeException("User not found: " + command.id()));
        // 値設定
        user.changeName(command.name());
        user.changeKind(command.userKind());
        // 保存
        userRepository.update(user);

    }

    /**
     * ユーザを削除する
     *
     * @param id 削除対象ID
     */
    public void delete(UserId id) {
        // 更新対象ユーザを取得
        Optional<User> target = userRepository.findById(id);
        // 取得できなければエラー
        User user = target.orElseThrow(() -> new RuntimeException("User not found: " + id));
        // 無効化
        user.deactivate();
        // 削除
        user.delete();
        // 保存
        userRepository.update(user);
    }

    /**
     * ユーザ検索する
     *
     * @param command 検索コマンド
     * @return ユーザ一覧
     */
    public List<User> search(UserSearchCommand command) {
        return userRepository.fetchAll(command.name(), command.userKind(), command.isActive());
    }

    /**
     * ユーザをID検索する
     *
     * @param id ユーザID
     * @return ユーザ
     */
    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }
}
