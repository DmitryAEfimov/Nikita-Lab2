package ru.nikita.lab2.dao.repository;

import ru.nikita.lab2.dao.entity.AccountEntity;
import ru.nikita.lab2.dao.entity.UserEntity;

import java.util.Set;

public interface AccountRepository {
    AccountEntity upsertAccount(AccountEntity account);

    void deleteAccount(AccountEntity account);

    Set<AccountEntity> findAccountsByUser(UserEntity user);
}
