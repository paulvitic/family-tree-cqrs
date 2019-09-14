package net.vitic.cqrs.familytree.port.adaptor.persistence;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Database {

    public static String url = "jdbc:h2:file:./db/familytree";

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static Session session(){
        if (sessionFactory==null) {
            initSessionFactory();
        }
        return sessionFactory.openSession();
    }

    public static void shutdown() {
        log.info("Shutting database down.");
        if (registry != null) StandardServiceRegistryBuilder.destroy(registry);
    }

    public static void migrate(){
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, "sa", null);
        flyway.migrate();
    }

    public static Integer createId() {
        return getNext("family_seq");
    }

    static Integer eventSequence() {
        return getNext("event_seq");
    }

    private static void initSessionFactory() {
        try {
            StandardServiceRegistryBuilder registryBuilder =
                    new StandardServiceRegistryBuilder();

            Map<String, String> settings = new HashMap<>();
            settings.put("hibernate.connection.driver_class", "org.h2.Driver");
            settings.put("hibernate.connection.url", url);
            settings.put("hibernate.connection.username", "sa");
            settings.put("hibernate.connection.password", "");
            settings.put("hibernate.connection.pool_size", "10");
            settings.put("hibernate.show_sql", "false");
            settings.put("hibernate.hbm2ddl.auto", "update");
            settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

            registryBuilder.applySettings(settings);
            registry = registryBuilder.build();
            MetadataSources sources = new MetadataSources(registry)
                    .addAnnotatedClassName("net.vitic.cqrs.familytree.domain.model.family.FamilyMember")
                    .addAnnotatedClassName("net.vitic.cqrs.familytree.domain.model.event.EventLog");

            sessionFactory = sources.buildMetadata().buildSessionFactory();

        } catch (Exception e) {
            log.error("Can not create db session factory.", e);
            System.out.println("SessionFactory creation failed");

            if (registry != null) {
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
    }

    private static Integer getNext(String sequence){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Number id = (Number) session.createNativeQuery("select nextval('"+ sequence +"')").uniqueResult();
            tx.commit();
            return id.intValue();
        } catch (Exception e) {
            if (tx.getStatus() == TransactionStatus.ACTIVE
                    || tx.getStatus() == TransactionStatus.MARKED_ROLLBACK) {
                tx.rollback();
            }
            throw new IllegalArgumentException(e);
        } finally {
            session.close();
        }
    }
}

