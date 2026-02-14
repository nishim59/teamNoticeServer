package com.example.teamNotice.domain.user.converter;

import com.example.teamNotice.domain.user.model.UserKind;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

/**
 * enumで定義した列挙子とコードを変換
 * このクラスがないとJPAが順番(ADMIN→0,NORMAL→1)でDBに格納してしまう
 */
@Converter(autoApply = true)
public class UserKindConverter implements AttributeConverter<UserKind, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserKind userKind) {
        if (userKind == null) return null;
        return userKind.getCode();
    }

    @Override
    public UserKind convertToEntityAttribute(Integer dbData) {
        if (dbData == null) return null;
        return UserKind.of(dbData);
    }
}
