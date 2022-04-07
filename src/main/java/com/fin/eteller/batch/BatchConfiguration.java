package com.fin.eteller.batch;

import com.fin.eteller.entity.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job transactionJob(JobCompletionNotificationListener listener, Step step1){
        return jobBuilderFactory.get("transactionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1")
                .<Transaction,Transaction>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public FlatFileItemReader<Transaction> reader(){
        DefaultLineMapper<Transaction> lineMapper =new DefaultLineMapper<>();
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer("|"));
        lineMapper.setFieldSetMapper(new TransactionMapper());

        return new FlatFileItemReaderBuilder<Transaction>()
                .name("transactionItemReader")
                .resource(new ClassPathResource("Maybank_Java Developer_Data Source.txt"))
                .delimited()
                .names(new String[]{"account","amount","description","date","time","customer"})
                .lineMapper(lineMapper)
                .linesToSkip(1)
                .build();
    }

    @Bean
    public TransactionItemProcessor processor(){
        return  new TransactionItemProcessor();
    }

    @Bean
    public TransactionItemWriter writer(){
        return new TransactionItemWriter();
    }
}
