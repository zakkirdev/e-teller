package com.fin.eteller.batch;

import com.fin.eteller.entity.Transaction;
import com.fin.eteller.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Slf4j
public class TransactionItemWriter implements ItemWriter<Transaction> {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void write(List<? extends Transaction> list) throws Exception {
        for(Transaction data : list){
            log.info(data.getDescription().toString());
            transactionRepository.save(data);
        }
    }
}
