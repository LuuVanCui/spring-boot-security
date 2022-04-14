package com.security.springsecutity.service;

import com.security.springsecutity.model.Role;
import com.security.springsecutity.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
}
