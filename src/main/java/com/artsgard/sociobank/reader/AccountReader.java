package com.artsgard.sociobank.reader;

import com.artsgard.sociobank.model.Account;
import com.artsgard.sociobank.model.AccountTransfer;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */
@Component
public class AccountReader {

    public FlatFileItemReader read() {
        CustomDateEditor ce = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false);

        return new FlatFileItemReaderBuilder<Account>()
                .linesToSkip(1)
                .name("socio-bank-reader")
                .resource(new FileSystemResource("accounts.csv"))
                .strict(true)
                .linesToSkip(1)
                .delimited().delimiter(";")
                .names(new String[]{"iban", "username", "balance", "currency", "creationDate", "active"})
                .targetType(Account.class).customEditors((Map<Class<?>, PropertyEditor>) ce)
                .build();
    }
}
//new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false)));