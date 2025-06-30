package Esport_Website.service;

import Esport_Website.entity.Users;

public interface UsersService {
    Users getUserById(Integer userId);
    Users getUserByUsername(String username);
    Users updateUser(Users user);
}
