package ru.nikita.lab2.service.impl;

import ru.nikita.lab2.api.dto.AccountDto;
import ru.nikita.lab2.api.dto.AccountInfoDto;
import ru.nikita.lab2.dao.entity.AccountEntity;
import ru.nikita.lab2.dao.repository.AccountRepository;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.service.AccountCRUDService;
import ru.nikita.lab2.service.util.ServiceFactory;

public class AccountServiceImpl implements AccountCRUDService {

    private final AccountRepository accountRepo;
    private final UserRepository userRepo;

    public AccountServiceImpl() {
        this.accountRepo = ServiceFactory.of(AccountRepository.class);
        this.userRepo = ServiceFactory.of(UserRepository.class);
    }

    @Override
    public AccountDto createAccount(AccountDto account) {
        var user = userRepo.findUserById(account.userId()).orElseThrow(() -> new IllegalArgumentException("User not found"));

        var entity = new AccountEntity(0, user);
        entity = accountRepo.upsertAccount(entity);

        return new AccountDto(entity.getId(), entity.getUser().getId());
    }

    @Override
    public void removeAccount(AccountDto account) {
        //        var entity = accountRepo.findAccountById(account.accountId());
        //
        //        if (entity == null) {
        //            throw new NoAccountFoundException();
        //        }
        //
        //        accountRepo.deleteAccount(entity);
    }

    @Override
    public AccountInfoDto getAccount(AccountDto account) {
        //        var entity = accountRepo.findAccountById(account.accountId());
        //
        //        if (entity == null) {
        //            throw new IllegalArgumentException("Account not found");
        //        }
        //
        //        var dto = new AccountDto(entity.getId(), entity.getUser().getId());
        //
        //        return new AccountInfoDto(dto, entity.getBalance());
        return null;
    }
}