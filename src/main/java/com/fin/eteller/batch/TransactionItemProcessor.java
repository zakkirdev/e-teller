package com.fin.eteller.batch;

import com.fin.eteller.entity.Transaction;
import org.springframework.batch.item.ItemProcessor;


public class TransactionItemProcessor implements ItemProcessor<Transaction,Transaction> {

    @Override
    public Transaction process(Transaction transaction) throws Exception {
        return transaction;
    }
}
