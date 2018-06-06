package ru.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.mantis.model.User;

import java.util.List;
import java.util.Set;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public List<User> users() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<User> result = (List<User>) session.createQuery( "mantis_user_table").list();
            for (User user : result) {
            System.out.println( user );
        }

        session.getTransaction().commit();
        session.close();
        return  result;
    }
}
