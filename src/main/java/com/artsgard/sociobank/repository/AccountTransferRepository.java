package com.artsgard.sociobank.repository;

import com.artsgard.sociobank.model.AccountTransfer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountTransferRepository extends JpaRepository<AccountTransfer, Long> {
    static final String ACCOUNT_TRANSFER_BY_IDS = 
             "SELECT * FROM account_transfer WHERE account_id=:accountId and account_transfer_id=:accountTransferId";
    
    @Query(value = ACCOUNT_TRANSFER_BY_IDS, nativeQuery = true)
    public AccountTransfer getByAccountIdAndAccountTransferId(@Param("accountId") Long accountId, @Param("accountTransferId") Long accountTransferId);
    
    List<AccountTransfer> findByAccountId(Long accountId);
    //List<AccountTransfer> findByIban(String iban);
}
