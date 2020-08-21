package com.artsgard.sociobank.service;

import com.artsgard.sociobank.dto.AccountTransferDTO;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author WillemDragstra
 */
@Service
public interface AccountTransferService {
    List<AccountTransferDTO> findAllAccountTransfers();
    AccountTransferDTO findAccountTransferByIds(Long accountId, Long accountTransferId);
    List<AccountTransferDTO> findAccountTransfersByAccountId(Long accountId);
    List<AccountTransferDTO> findAccountTransfersByUsername(String username);
    List<AccountTransferDTO> findAccountTransfersByIban(String iban);
    AccountTransferDTO saveAccountTransfer(AccountTransferDTO accountDTO);
}
