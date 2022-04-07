package com.fin.eteller.service;

import com.fin.eteller.entity.Role;
import com.fin.eteller.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUser(String email);
    List<User> getUsers();
}
