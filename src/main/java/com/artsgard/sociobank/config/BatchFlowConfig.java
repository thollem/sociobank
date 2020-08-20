package com.artsgard.sociobank.config;

import com.artsgard.sociobank.model.Account;
import com.artsgard.sociobank.prossesor.SocioAccountProcessor;
import com.artsgard.sociobank.reader.SocioAccountReader;
import com.artsgard.sociobank.writer.SocioAccountWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author artsgard
 */

@Configuration
@EnableBatchProcessing
public class BatchFlowConfig {
    
    @Autowired
    @Qualifier("dbTransactionManager") 
    private PlatformTransactionManager transactionManager;
    
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;
    
    @Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private SocioAccountProcessor socioAccountProcessor;

    @Autowired
    private SocioAccountReader socioAccountReader;

    @Autowired
    private SocioAccountWriter socioAccountWriter;

    @Bean(name = "socioaccountjob")
    public Job socioAccountJob() throws Exception {
        return jobBuilders.get("batchSocioAccountJob")
                .repository(jobRepository)
                .start(socioAccountStep())
                .build();
    }

    @Bean
    public Step socioAccountStep() throws Exception {
        return stepBuilders.get("batchSocioAccountStep")
                .<Account, Account>chunk(20)
                .reader(socioAccountReader.read())
                .processor(socioAccountProcessor)
                .writer(socioAccountWriter)
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