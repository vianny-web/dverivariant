package com.vianny.dverivariant.services;

import com.vianny.dverivariant.dto.request.auth.SignInRequest;
import com.vianny.dverivariant.dto.request.auth.SignUpRequest;
import com.vianny.dverivariant.exceptions.requiredException.UnregisteredRequiredException;
import com.vianny.dverivariant.models.Accounts;
import com.vianny.dverivariant.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @Autowired
    public void setUserRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public void signUpAccount(SignUpRequest signUpRequest) {
        if (accountRepository.existsAccountByLogin(signUpRequest.getLogin())) {
            throw new UnregisteredRequiredException(HttpStatus.BAD_REQUEST, "Выберите другой адрес электронной почты");
        }

        Accounts accounts = new Accounts();
        String hashed = passwordEncoder.encode(signUpRequest.getPassword());

        accounts.setLogin(signUpRequest.getLogin());
        accounts.setPassword(hashed);

        accountRepository.save(accounts);
    }

    public Authentication signInAccount(SignInRequest signInRequest) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getLogin(),
                        signInRequest.getPassword()));
    }
}
