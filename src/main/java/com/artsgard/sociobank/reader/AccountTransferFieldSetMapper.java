package com.artsgard.sociobank.reader;

import com.artsgard.sociobank.model.AccountTransfer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@Component
public class AccountTransferFieldSetMapper implements FieldSetMapper {
    
    @Override
    public AccountTransfer mapFieldSet(FieldSet fieldSet) {
        AccountTransfer transfer = new AccountTransfer();
        transfer.setAccountId(fieldSet.readLong("accountId"));
        transfer.setAccountTransferId(fieldSet.readLong("accountTransferId"));
        transfer.setAmount(fieldSet.readBigDecimal("amount"));
        transfer.setDescription(fieldSet.readString("description"));
        transfer.setTransferDate(fieldSet.readDate("transferDate"));
        return transfer;
    }
}