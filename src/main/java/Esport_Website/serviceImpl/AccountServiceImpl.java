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

import java.util.List;
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
                .password(passwordEncoder.encode(request.getPassword()))
                .point(0)
                .user(user)
                .build();

        return accountDAO.save(account);
    }

    @Override
    public Account login(LoginRequest request) {
        System.out.println("=== DEBUG LOGIN ===");
        System.out.println("Username: " + request.getUsername());
        System.out.println("Password length: " + (request.getPassword() != null ? request.getPassword().length() : "null"));
        
        Optional<Account> accountOpt = accountDAO.findByUsername(request.getUsername());
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            String storedPassword = account.getPassword();
            
            System.out.println("Found account: " + account.getUsername());
            System.out.println("Stored password: " + storedPassword);
            System.out.println("Stored password starts with $2a$: " + storedPassword.startsWith("$2a$"));
            System.out.println("Stored password starts with $2b$: " + storedPassword.startsWith("$2b$"));
            System.out.println("Stored password starts with $2y$: " + storedPassword.startsWith("$2y$"));
            
            // Kiểm tra nếu mật khẩu đã được mã hóa BCrypt
            if (storedPassword.startsWith("$2a$") || storedPassword.startsWith("$2b$") || storedPassword.startsWith("$2y$")) {
                System.out.println("Password is BCrypt encoded");
                // Mật khẩu đã được mã hóa BCrypt
                boolean matches = passwordEncoder.matches(request.getPassword(), storedPassword);
                System.out.println("BCrypt matches: " + matches);
                if (matches) {
                    return account;
                } else {
                    throw new RuntimeException("Sai mật khẩu");
                }
            } else {
                System.out.println("Password is plain text");
                // Mật khẩu plain text (chưa được mã hóa)
                boolean plainTextMatches = request.getPassword().equals(storedPassword);
                System.out.println("Plain text matches: " + plainTextMatches);
                if (plainTextMatches) {
                    // Tự động mã hóa mật khẩu và cập nhật database
                    String encodedPassword = passwordEncoder.encode(request.getPassword());
                    System.out.println("Encoding password to: " + encodedPassword);
                    account.setPassword(encodedPassword);
                    accountDAO.save(account);
                    return account;
                } else {
                    throw new RuntimeException("Sai mật khẩu");
                }
            }
        } else {
            System.out.println("Account not found");
            throw new RuntimeException("Tài khoản không tồn tại");
        }
    }

    @Override
    public void encryptAllPlainTextPasswords() {
        List<Account> allAccounts = accountDAO.findAll();
        int encryptedCount = 0;
        
        for (Account account : allAccounts) {
            String password = account.getPassword();
            // Kiểm tra nếu mật khẩu chưa được mã hóa BCrypt
            if (!password.startsWith("$2a$") && !password.startsWith("$2b$") && !password.startsWith("$2y$")) {
                // Mã hóa mật khẩu plain text
                account.setPassword(passwordEncoder.encode(password));
                accountDAO.save(account);
                encryptedCount++;
            }
        }
        
        System.out.println("Đã mã hóa " + encryptedCount + " mật khẩu");
    }
}
