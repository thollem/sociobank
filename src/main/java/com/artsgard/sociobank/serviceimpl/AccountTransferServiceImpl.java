package com.artsgard.sociobank.serviceimpl;

import com.artsgard.sociobank.dto.AccountTransferDTO;
import com.artsgard.sociobank.model.AccountTransfer;
import com.artsgard.sociobank.repository.AccountRepository;
import com.artsgard.sociobank.repository.AccountTransferRepository;
import com.artsgard.sociobank.service.AccountTransferService;
import com.artsgard.sociobank.service.MapperService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
    public List<AccountTransferDTO> findAccountTransfersByAccountId(Long accountId) {
        return null; // do query
    }

    @Override
    public AccountTransferDTO findAccountTransfersByIban(String iban) {
        return null; // do query
    }

    @Override
    public AccountTransferDTO updateAccountTransfer(AccountTransferDTO transferDTO) {
        
        AccountTransfer transfer = accountTransferRepository.getAccountTransferByAccountIdAndAccountTransferId(transferDTO.getAccountId(), transferDTO.getAccountTransferId());
        transfer.setTransferDate(new Timestamp(System.currentTimeMillis()));
        transfer.setAmount(transferDTO.getAmount());
        transfer.setDescription(transferDTO.getDescription());
        return map.mapAccountTransferToAccountTransferDTO(accountTransferRepository.save(transfer));
    }

    @Override
    public void deleteAccountTransferById(Long accountId, Long accountTransferId) {
        // write query
    }


}
