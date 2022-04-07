package com.fin.eteller.service;

import com.fin.eteller.entity.Transaction;
import com.fin.eteller.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl  implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionByCustomer(String customerId) {
        return transactionRepository.findByCustomer(customerId);
    }

    @Override
    public Page<Transaction> findByPagination(int pageNo,int size) {
        Pageable pageable = PageRequest.of(pageNo,size);
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Page<Transaction> findByCustomer(int pageNo, int pageSize, String customerId) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return transactionRepository.findAllByCustomer(customerId,pageable);
    }

    @Override
    public Page<Transaction> findByAccount(int pageNo, int pageSize, String accountNumber) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return transactionRepository.findAllByAccount(accountNumber,pageable);
    }

    @Override
    public Page<Transaction> findByDescription(int pageNo, int pageSize, String description) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        return transactionRepository.findAllByDescription(description,pageable);
    }

}
