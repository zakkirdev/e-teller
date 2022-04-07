package com.fin.eteller.repository;


import com.fin.eteller.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByCustomer(String customerId);

    Page<Transaction> findAllByCustomer(String customerId, Pageable pageable);

    Page<Transaction> findAllByAccount(String accountNumber, Pageable pageable);

    Page<Transaction> findAllByDescription(String description, Pageable pageable);
}
