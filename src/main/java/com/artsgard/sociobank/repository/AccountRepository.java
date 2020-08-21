package com.artsgard.sociobank.repository;

import com.artsgard.sociobank.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findAccountByIban(String iban);
    Optional<Account> findAccountByUsername(String username);
}
