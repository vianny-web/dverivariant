package com.vianny.dverivariant.repositories;

import com.vianny.dverivariant.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Accounts, Long> {
    Optional<Accounts> findAccountByLogin(String login);
    Boolean existsAccountByLogin(String login);

}
