package spring.core.dao.impl;

import lombok.Setter;
import spring.core.dao.UserDao;
import spring.core.model.User;
import spring.core.storage.UserStorage;

import java.util.List;
import java.util.Optional;

@Setter
public class UserDaoImpl implements UserDao {
    private UserStorage userStorage;

    @Override
    public User add(User user) {
        return userStorage.add(user);
    }

    @Override
    public Optional<User> getUserById(long userId) {
        return userStorage.getUserById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userStorage.getUserByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userStorage.getUsersByName(name, pageSize, pageNum);
    }

    @Override
    public Optional<User> updateUser(User user) {
        return userStorage.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userStorage.deleteUser(userId);
    }
}
