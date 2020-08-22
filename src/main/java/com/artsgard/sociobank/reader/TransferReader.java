package com.artsgard.sociobank.reader;

import com.artsgard.sociobank.model.Account;
import com.artsgard.sociobank.model.AccountTransfer;
import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import sun.util.calendar.BaseCalendar.Date;

/**
 *
 * @author artsgard
 */
@Component
public class TransferReader {

    public FlatFileItemReader read() {
        CustomDateEditor ce = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false);
        return new FlatFileItemReaderBuilder<AccountTransfer>()
                .name("socio-bank-transfer-reader")
                .resource(new FileSystemResource("transfers.csv"))
                //.encoding(SeedConstants.DEFAULT_CHARSET)
                .linesToSkip(1)
                .strict(true)
                .delimited().delimiter(";")
                .names(new String[]{"accountId", "accountTransferId", "amount", "description", "transferDate"})
                .targetType(AccountTransfer.class).customEditors(Collections.singletonMap(Date.class,
                    new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), false)))
                
                .build();
    }
}

 