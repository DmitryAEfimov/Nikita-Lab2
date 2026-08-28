package ru.nikita.lab2.dao.utils;

import jakarta.persistence.*;
import ru.nikita.lab2.dao.entity.AccountEntity;
import ru.nikita.lab2.dao.entity.OperationEntity;
import ru.nikita.lab2.dao.entity.UserEntity;

public final class EntityManagerUtil {
    private final static String PERSISTENCE_UNIT = "Lab2PU";
    private static EntityManagerFactory emf;

    private EntityManagerUtil() {
    }

    private static EntityManagerFactory getEntityManagerFactory() {
        synchronized (EntityManagerFactory.class) {
            if (emf == null) {
                var emfConfig = new PersistenceConfiguration(PERSISTENCE_UNIT);
                emfConfig.provider("org.hibernate.jpa.HibernatePersistenceProvider");
                emfConfig.transactionType(PersistenceUnitTransactionType.RESOURCE_LOCAL);

                var dbHost = System.getenv("DB_HOST");
                var dbName = System.getenv("DB_NAME");

                emfConfig.property("jakarta.persistence.jdbc.driver", "org.postgresql.Driver")
                        .property("jakarta.persistence.jdbc.url", "jdbc:postgresql://" + dbHost + "/" + dbName)
                        .property("jakarta.persistence.jdbc.user", System.getenv("APP_USER"))
                        .property("jakarta.persistence.jdbc.password", System.getenv("APP_PASSWORD"))
                        .property("jakarta.persistence.lock.timeout", "100")
                        .property("jakarta.persistence.query.timeout", "100")
                        .property("hibernate.hbm2ddl.auto", "validate")
                        .property("hibernate.show_sql", "true")
                        .property("org.hibernate.flushMode", "COMMIT")
                        .property("org.hibernate.cacheable", "true");

                emfConfig.managedClass(UserEntity.class)
                        .managedClass(AccountEntity.class)
                        .managedClass(OperationEntity.class);

                emf = Persistence.createEntityManagerFactory(emfConfig);
            }
        }

        return emf;
    }

    /**
     * @return new entity manager instance
     */
    public static EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }
}
