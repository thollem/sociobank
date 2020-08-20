package com.artsgard.sociobank.service;

import com.artsgard.sociobank.dto.AccountTransferDTO;
import com.artsgard.sociobank.model.AccountTransfer;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author WillemDragstra
 */
@Service
public interface AccountTransferService {
    List<AccountTransferDTO> findAllAccountTransfers();
    List<AccountTransferDTO> findAccountTransfersByAccountId(Long accountId);
    AccountTransferDTO findAccountTransfersByIban(String iban);
    AccountTransferDTO updateAccountTransfer(AccountTransferDTO accountDTO);
    void deleteAccountTransferById(Long accountId, Long accountTransferId);
}
