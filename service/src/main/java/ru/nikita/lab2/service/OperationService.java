package ru.nikita.lab2.service;

import ru.nikita.lab2.api.dto.AccountDto;
import ru.nikita.lab2.api.dto.OperationDto;

import java.util.List;

public interface OperationService {
    void provideOperation(OperationDto operation);
    List<OperationDto> getHistory(AccountDto account);
}
