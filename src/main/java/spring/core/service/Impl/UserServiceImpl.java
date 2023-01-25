package spring.core.service.Impl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.core.dao.UserDao;
import spring.core.model.User;
import spring.core.model.impl.UserImpl;
import spring.core.service.UserService;
import spring.core.util.IdGenerator;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Setter
@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private IdGenerator idGenerator;

    @Autowired
    public UserServiceImpl(UserDao userDao, IdGenerator idGenerator) {
        this.userDao = userDao;
        this.idGenerator = idGenerator;
    }

    @Override
    public User create(User user) {
        long generatedId = idGenerator.generateId(UserImpl.class);
        user.setId(generatedId);

        log.info("Creating user with id " + user.getId());
        userDao.add(user);
        log.info("User was created successfully");
        return user;
    }

    @Override
    public User getUserById(long userId) {
        log.info("Getting user by id: " + userId);
        Optional<User> userById = userDao.getUserById(userId);
        if (userById.isPresent()) {
            log.info("User was got successfully: " + userById.get());
            return userById.get();
        } else {
            throw new NoSuchElementException("Can't get user with id: " + userById);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        log.info("Getting user by email: " + email);
        Optional<User> userByEmail = userDao.getUserByEmail(email);
        if (userByEmail.isPresent()) {
            log.info("User was got successfully: " + userByEmail.get());
            return userByEmail.get();
        } else {
            throw new NoSuchElementException("Can't get user with email: " + email);
        }
    }

    @Override
    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        log.info("Getting users by name");
        List<User> users = userDao.getUsersByName(name, pageSize, pageNum);
        if (users.isEmpty()) {
            log.info("No users with this name: " + name);
        } else {
            log.info("users were got successfully: " + users);
        }
        return users;
    }

    @Override
    public User updateUser(User user) {
        log.info("Updating user with id " + user.getId());
        Optional<User> userFromDb = userDao.updateUser(user);
        if (userFromDb.isPresent()) {
            log.info("User was updated successfully");
            return userFromDb.get();
        } else {
            throw new NoSuchElementException("Can't update user with id: " + user.getId());
        }
    }

    @Override
    public boolean deleteUser(long userId) {
        log.info("Deleting user with id " + userId);
        boolean isDeleted = userDao.deleteUser(userId);
        if (isDeleted) {
            log.info("User was deleted successfully");
            return true;
        }
        log.info("No user was found with such id:  " + userId);
        return false;
    }
}
