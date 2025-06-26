package Esport_Website.serviceImpl;

import Esport_Website.DAO.AccountDAO;
import Esport_Website.DAO.UsersDAO;
import Esport_Website.dto.LoginRequest;
import Esport_Website.dto.RegisterRequest;
import Esport_Website.entity.Account;
import Esport_Website.entity.Users;
import Esport_Website.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private UsersDAO usersDAO;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account register(RegisterRequest request) {
        Optional<Account> existing = accountDAO.findByUsername(request.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("Tài khoản đã tồn tại");
        }

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Mật khẩu xác nhận không đúng");
        }

        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .remainingPoint(0)
                .build();
        usersDAO.save(user);

        Account account = Account.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword())) // mã hóa mật khẩu
                .point(0)
                .user(user)
                .build();

        return accountDAO.save(account);
    }

    @Override
    public Account login(LoginRequest request) {
        Optional<Account> accountOpt = accountDAO.findByUsername(request.getUsername());
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            if (passwordEncoder.matches(request.getPassword(), account.getPassword())) {
                return account;
            } else {
                throw new RuntimeException("Sai mật khẩu");
            }
        } else {
            throw new RuntimeException("Tài khoản không tồn tại");
        }
    }
}
