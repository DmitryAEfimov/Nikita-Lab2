package ru.nikita.lab2.application;

import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;
import ru.nikita.lab2.api.enumeration.OpType;
import ru.nikita.lab2.application.exception.ExceptionHandlerResolver;
import ru.nikita.lab2.application.request.RequestDispatcher;
import ru.nikita.lab2.application.request.RequestProcessor;
import ru.nikita.lab2.dao.repository.AccountRepository;
import ru.nikita.lab2.dao.repository.UserRepository;
import ru.nikita.lab2.dao.repository.impl.AccountRepositoryImpl;
import ru.nikita.lab2.dao.repository.impl.UserRepositoryImpl;
import ru.nikita.lab2.service.AccountCRUDService;
import ru.nikita.lab2.service.FriendService;
import ru.nikita.lab2.service.OperationService;
import ru.nikita.lab2.service.UserCRUDService;
import ru.nikita.lab2.service.impl.AccountServiceImpl;
import ru.nikita.lab2.service.impl.FriendServiceImpl;
import ru.nikita.lab2.service.impl.OperationServiceImpl;
import ru.nikita.lab2.service.impl.UserServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Application {
    private static final String STOP_WORD = "stop";
    private final ExceptionHandlerResolver exResolver;
    private final BufferedReader reader;
    private boolean stop;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RequestDispatcher requestDispatcher;

    private final UserCRUDService userService;
    private final FriendService friendService;
    private final AccountCRUDService accountService;
    private final OperationService operationService;

    public Application() {
        this.exResolver = new ExceptionHandlerResolver();
        this.reader = new BufferedReader(new InputStreamReader(System.in));

        this.userRepository = new UserRepositoryImpl();
        this.accountRepository = new AccountRepositoryImpl();


        this.userService = new UserServiceImpl(userRepository);
        this.requestDispatcher = new RequestDispatcher(userService);
        this.friendService = new FriendServiceImpl(userRepository);
        this.accountService = new AccountServiceImpl(
                accountRepository,
                userRepository
        );
        this.operationService = new OperationServiceImpl(accountRepository);
    }

    public void start() throws IOException {
        readme();
        while (!stop) {
            var command = reader.readLine();

            if (STOP_WORD.equalsIgnoreCase(command)) {
                terminate();
            } else {
                processRequest(requestDispatcher.dispatch(command));
            }
        }
    }

    public void terminate() {
        stop = true;
    }

    private void processRequest(RequestProcessor<?> processor) {
        if (processor != null) {
            try {
                processor.execute();
            } catch (Exception e) {
                var exceptionHandler = exResolver.resolve(processor.getContext(), e);

                if (exceptionHandler != null) {
                    exceptionHandler.execute();
                } else {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readme() {
        System.out.println("""
                Для выхода введите:\s""" + STOP_WORD + """
                \nПоддерживаемые команды и формат данных: <cmd_name> <json-like payload>
                Названия команд регистрозависимы. Кавычки в payload должны быть экранированы
                Пример: deleteUser {\\"id\\": \\"019fe39d-e135-738c-a1af-10d8e4dffc7d\\"}
                - User (attrs - id:UUID!, login:String!, name:String!, age:integer!, gender:Gender, hairColor:HairColor, accounts:[Account], friends:[User]):
                  -- createUser {login!, name!, age!, gender, hairColor}
                  -- updateUser {id!, name, age, gender, hairColor}. В запрос передаются только изменяемые атрибуты. <attr>: null - очистка существующего значения
                  -- deleteUser {id!}
                  -- readUserInfo {id!}
                  -- addFriend {id!, friendId!}
                  -- deleteFriend {id!, friendId!}
                - Account (attrs - id:UUID!, user:User!, balance:decimal, operations:[Operation]):
                  -- createAccount {userId:UUID!}
                  -- deleteAccount {id:UUID!}
                  -- readAccountInfo {id!}
                  -- showHistory {id!, fromDate:date, toDate:date} dates in RFC3339 format
                  -- deposit {id!, amount!}
                  -- withdraw {id!, amount!}
                  -- transfer {id!, amount!, destination!}
                - Operation (attrs - id:UUID!, account:Account!, opType:OpType!, destination:Account, opdate:date, amount:decimal!, commission:decimal) date in RFC3339 format
                - Gender:\s""" + Arrays.toString(Gender.values()) + """
                \n- HairColor:\s""" + Arrays.toString(HairColor.values()) + """
                \n- OpType:\s""" + Arrays.toString(OpType.values()) + """
                \n""");
    }
}
