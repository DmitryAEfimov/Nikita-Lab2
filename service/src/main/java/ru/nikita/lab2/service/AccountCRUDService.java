package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.AccountDto;
import ru.nikita.lab2.api.dto.AccountInfoDto;

public interface AccountCRUDService {
    AccountDto createAccount(AccountDto account);

    void removeAccount(AccountDto account);

    AccountInfoDto getAccount(AccountDto account);
}
