package Esport_Website.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Esport_Website.DAO.AccountDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.entity.Account;
import Esport_Website.entity.Users;
import Esport_Website.service.UsersService;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Users getUserById(Integer userId) {
        Optional<Users> user = usersDAO.findById(userId);
        return user.orElse(null);
    }

    @Override
    public Users getUserByUsername(String username) {
        Optional<Account> account = accountDAO.findByUsername(username);
        if (account.isPresent()) {
            return account.get().getUser();
        }
        return null;
    }

    @Override
    public Users updateUser(Users user) {
        return usersDAO.save(user);
    }
}
