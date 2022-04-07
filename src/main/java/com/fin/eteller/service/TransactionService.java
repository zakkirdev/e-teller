package com.fin.eteller.service;

import com.fin.eteller.entity.Transaction;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    Transaction saveTransaction(Transaction transaction);
    List<Transaction> getTransactionByCustomer(String customerId);

    Page<Transaction> findByPagination(int pageNo,int size);

    Page<Transaction> findByCustomer(int pageNo, int pageSize,String customerId);

    Page<Transaction> findByAccount(int pageNo, int pageSize, String accountNumber);

    Page<Transaction> findByDescription(int pageNo, int pageSize, String description);
}
