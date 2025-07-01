package Esport_Website.service;

import Esport_Website.dto.AccountRequest;
import Esport_Website.dto.LoginRequest;
import Esport_Website.dto.RegisterRequest;
import Esport_Website.entity.Account;

public interface AccountService {
    Account login(LoginRequest request);
    Account register(RegisterRequest request);
    void encryptAllPlainTextPasswords();
    Account changePassword(AccountRequest request);
}
