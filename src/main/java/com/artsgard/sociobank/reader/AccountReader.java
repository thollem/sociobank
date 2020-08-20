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
public class AccountReader {

    public FlatFileItemReader read() {

        return new FlatFileItemReaderBuilder<Account>()
                .linesToSkip(1)
                
                .name("socio-account-reader")
                .resource(new FileSystemResource("this/file/does/not/exist"))
                .delimited()
                
                .names("iban", "username", "balance", "currency", "creationDate", "active")
                .targetType(Account.class)
                .strict(false)
                .build();
    }
}
