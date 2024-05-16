package com.vianny.dverivariant.services;

import com.vianny.dverivariant.models.Accounts;
import com.vianny.dverivariant.repositories.AccountRepository;
import com.vianny.dverivariant.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private AccountRepository accountRepository;
    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Accounts accounts = accountRepository.findAccountByLogin(login).orElseThrow(() -> new UsernameNotFoundException(
                String.format("Пользователь '%s' не найден", login)
        ));

        return UserDetailsImpl.build(accounts);
    }
}
