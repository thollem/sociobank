package com.artsgard.sociobank.reader;

import com.artsgard.sociobank.model.Account;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@Component
public class TransferReader {

    public FlatFileItemReader read() {

        return new FlatFileItemReaderBuilder<Account>()
                .name("socio-transfer-reader")
                .resource(new FileSystemResource("this/file/does/not/exist"))
                .delimited()
                .names("accountId", "accountTransferId", "amount", "description", "transferDate")
                .targetType(Account.class)
                .strict(false)
                .build();
    }
}
/*
The app starts-up, pre-loading (batch-style) the following data,
Accounts: 
1;es23;200.00;EUR
2;es24;1740.67;GBP
3;es25;10743.89;USD

I simplified the iban (which should be unique)

Transfers:
1;2;100;Yesterday’s Lunch;2018-11-01 09:03:56
2;3;50.67;Online Voucher Gift;2018-11-02 15:37:54
*/