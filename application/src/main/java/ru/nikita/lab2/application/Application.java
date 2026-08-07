package ru.nikita.lab2.application;

import ru.nikita.lab2.api.enumeration.Gender;
import ru.nikita.lab2.api.enumeration.HairColor;
import ru.nikita.lab2.api.enumeration.OpType;
import ru.nikita.lab2.application.exception.ExceptionHandlerResolver;
import ru.nikita.lab2.application.request.RequestDispatcher;
import ru.nikita.lab2.application.request.RequestProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Application {
    private static final String STOP_WORD = "stop";
    private final ExceptionHandlerResolver exResolver;
    private final BufferedReader reader;
    private boolean stop;

    public Application() {
        this.exResolver = new ExceptionHandlerResolver();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.stop = true;
    }

    public void start() throws IOException {
        readme();
        while (!stop) {
            var command = reader.readLine();

            if (STOP_WORD.equalsIgnoreCase(command)) {
                terminate();
            } else {
                processRequest(RequestDispatcher.dispatch(command));
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
                exceptionHandler.execute();
            }
        }
    }

    private void readme() {
        System.out.println("""
                Для выхода введите:\s""" + STOP_WORD + """
                Поддерживаемые команды (case-sensitive) и формат данных: <cmd_name> <json-like with escaped quotes>
                - User (attrs - id:UUID!, login:String!, name:String!, age:integer!, gender:Gender, hairColor:HairColor, accounts:[Account], friends:[User]):
                  -- createUser {login!, name!, age!, gender, hairColor}
                  -- updateUser {id!, name, age, gender, hairColor}. Include only modified attrs. <attr>: null - purge current value
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
                - HairColor:\s""" + Arrays.toString(HairColor.values()) + """
                - OpType:\s""" + Arrays.toString(OpType.values()) + """
                """);
    }
}
