package spring.core.service;

import spring.core.model.User;

import java.util.List;

public interface UserService {
    User create(User user);

    User getUserById(long userId);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name, int pageSize, int pageNum);

    User updateUser(User user);

    boolean deleteUser(long userId);
}
