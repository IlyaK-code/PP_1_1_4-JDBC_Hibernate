package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserDao DAO;

    public UserServiceImpl() {
        //DAO = new UserDaoJDBCImpl();
        DAO = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        DAO.createUsersTable();
    }

    public void dropUsersTable() {
        DAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        DAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        DAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return DAO.getAllUsers();
    }

    public void cleanUsersTable() {
        DAO.cleanUsersTable();
    }
}
