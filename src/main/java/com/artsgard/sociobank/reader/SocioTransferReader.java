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
public class SocioTransferReader {

    public FlatFileItemReader read() {

        return new FlatFileItemReaderBuilder<Account>()
                .name("socioaccountreader")
                .resource(new FileSystemResource("this/file/does/not/exist"))
                .delimited()
                .names("first", "second", "third")
                .targetType(Account.class)
                .strict(false)
                .build();
    }
}
