package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionHibernate();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (session.createSQLQuery("SHOW TABLE STATUS LIKE 'users'").executeUpdate() == 0) {
                String createSQL = "CREATE TABLE `users` (\n" +
                        "  `Id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                        "  `Name` VARCHAR(45) NOT NULL,\n" +
                        "  `LastName` VARCHAR(45) NOT NULL,\n" +
                        "  `age` TINYINT NOT NULL,\n" +
                        "  PRIMARY KEY (`Id`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
                session.createSQLQuery(createSQL).executeUpdate();
                session.getTransaction().commit();
            } else {
                System.out.println("Таблица уже существует!");
            }
        }
    }

    @Override
    public void dropUsersTable() {
        String dropSQL = "DROP TABLE users";
        try (SessionFactory factory = Util.getSessionHibernate();
             Session session = factory.openSession()) {
            session.beginTransaction();
            if (session.createSQLQuery("SHOW TABLE STATUS LIKE 'users'").executeUpdate() == -1) {
                session.createSQLQuery(dropSQL).executeUpdate();
                session.getTransaction().commit();
            } else {
                System.out.println("Таблицу невозможно удолить повторно!");
            }
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (SessionFactory factory = Util.getSessionHibernate();
             Session session = factory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        System.out.printf("User с именем - %s добавлен в базу\n", name);
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory factory = Util.getSessionHibernate();
             Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser;
        try (SessionFactory factory = Util.getSessionHibernate();
             Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            listUser = session.createCriteria(User.class).list();
            transaction.commit();
        }
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory factory = Util.getSessionHibernate();
             Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        }
    }
}

