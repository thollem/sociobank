package com.artsgard.sociobank.prossesor;

import com.artsgard.sociobank.exception.ResourceNotFoundException;
import com.artsgard.sociobank.model.Account;
import com.artsgard.sociobank.model.AccountTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import com.artsgard.sociobank.repository.AccountRepository;
import com.artsgard.sociobank.repository.AccountTransferRepository;
import java.util.Optional;

/**
 *
 * @author artsgard
 */
@Component
public class TransferProcessor implements ItemProcessor<AccountTransfer, AccountTransfer> {

    @Autowired
    private AccountTransferRepository transferRepo;
    
    @Autowired
    private AccountRepository accountRepo;

    @Override
    public AccountTransfer process(AccountTransfer transfer) throws Exception {
        Optional<Account> opt1 = accountRepo.findById(transfer.getAccountId());
        Optional<Account> opt2 = accountRepo.findById(transfer.getAccountTransferId());
        
        if (opt1.isPresent() && opt2.isPresent()) {
            transfer.setAccount(opt1.get());
            transfer.setAccountTransfer(opt2.get());
        } else {
            //throw new ResourceNotFoundException("");
            transfer = null;
        }
        return transfer;
    }
}