package com.artsgard.sociobank.serviceimpl;

import com.artsgard.sociobank.dto.AccountTransferDTO;
import com.artsgard.sociobank.model.Account;
import com.artsgard.sociobank.model.AccountTransfer;
import com.artsgard.sociobank.repository.AccountRepository;
import com.artsgard.sociobank.repository.AccountTransferRepository;
import com.artsgard.sociobank.service.AccountTransferService;
import com.artsgard.sociobank.service.MapperService;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author artsgard
 */
@Service
public class AccountTransferServiceImpl implements AccountTransferService {

    org.slf4j.Logger logger = LoggerFactory.getLogger(AccountTransferServiceImpl.class);

    @Autowired
    private AccountRepository accountRepò;
    
    @Autowired
    private MapperService map;
    
    @Autowired
    private AccountTransferRepository accountTransferRepository;

    @Override
    public List<AccountTransferDTO> findAllAccountTransfers() {
        List<AccountTransferDTO> list = new ArrayList();
        accountTransferRepository.findAll().forEach(at -> {
            list.add(map.mapAccountTransferToAccountTransferDTO(at));
        });
        return list;
    }
    
    @Override
    public AccountTransferDTO findAccountTransferByIds(Long accountId, Long accountTransferId) {
        AccountTransfer tran = accountTransferRepository.getByAccountIdAndAccountTransferId(accountId, accountTransferId);
        return map.mapAccountTransferToAccountTransferDTO(tran);
    }

    @Override
    public List<AccountTransferDTO> findAccountTransfersByAccountId(Long accountId) {
        List<AccountTransfer> trans = accountTransferRepository.findByAccountId(accountId);
        List<AccountTransferDTO> list = new ArrayList();
        for (AccountTransfer tran: trans) {
            list.add(map.mapAccountTransferToAccountTransferDTO(tran));
        }
        return list;
    }

    @Override
    public List<AccountTransferDTO> findAccountTransfersByIban(String iban) {
        List<AccountTransfer> trans = accountTransferRepository.findByIban(iban);
        List<AccountTransferDTO> list = new ArrayList();
        for (AccountTransfer tran: trans) {
            list.add(map.mapAccountTransferToAccountTransferDTO(tran));
        }
        return list;
    }
    
    @Override
    public List<AccountTransferDTO> findAccountTransfersByUsername(String username) {
        List<AccountTransfer> trans = accountTransferRepository.findByUsername(username);
        List<AccountTransferDTO> list = new ArrayList();
        for (AccountTransfer tran: trans) {
            list.add(map.mapAccountTransferToAccountTransferDTO(tran));
        }
        return list;
    }
    
    @Override
    public AccountTransferDTO saveAccountTransfer(AccountTransferDTO transferDTO) {
        Optional<Account> optAccount1 = accountRepò.findById(transferDTO.getAccountId());
        Optional<Account> optAccount2 = accountRepò.findById(transferDTO.getAccountTransferId());
        
        if (optAccount1.isPresent() && optAccount2.isPresent()) {
            Account acc1 = optAccount1.get();
            Account acc2 = optAccount2.get();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            AccountTransfer tran = new AccountTransfer(transferDTO.getAccountId(), transferDTO.getAccountTransferId(), acc1, 
                    acc2, transferDTO.getAmount(), transferDTO.getDescription(), now);
            
            BigDecimal debet = transferDTO.getAmount();
            BigDecimal credit = debet.multiply(convertion(acc1.getCurrency(), acc2.getCurrency()));
            
            acc1.setBalance(acc1.getBalance().subtract(debet));
            acc2.setBalance(acc2.getBalance().add(debet));
            accountRepò.save(acc1);
            accountRepò.save(acc2);
            return map.mapAccountTransferToAccountTransferDTO(accountTransferRepository.save(tran));
        } else {
            return null;
        }
    }

    private BigDecimal convertion(String currency1, String currency2) {
        if (currency1.equals(currency2)) {
            return new BigDecimal("1");
        } else {
            // refernece to currency service
            return new BigDecimal("1");
        }
    }
}
