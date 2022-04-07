package com.fin.eteller.controller;

import com.fin.eteller.entity.Transaction;
import com.fin.eteller.repository.TransactionRepository;
import com.fin.eteller.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/history/{pageNo}")
    public List<Transaction> getTransactionByPage(@PathVariable(value = "pageNo") int pageNo){
        int pageSize = 10;
        Page<Transaction> page = transactionService.findByPagination(pageNo,pageSize);
        List<Transaction> list = page.getContent();
        return list;
    }

    @GetMapping("/history/customer/{pageNo}")
    public List<Transaction> getTransactionByCustomer(@PathVariable(value = "pageNo") int pageNo, @RequestBody Map<String, Object> userMap){
        int pageSize = 10;
        String customerId = userMap.get("customerId").toString();
        Page<Transaction> page = transactionService.findByCustomer(pageNo,pageSize,customerId);
        List<Transaction> list = page.getContent();
        return list;
    }

    @GetMapping("history/account/{pageNo}")
    public List<Transaction> getTransactionByAccount(@PathVariable(value = "pageNo") int pageNo, @RequestBody Map<String, Object> userMap){
        int pageSize = 10;
        String accountNumber = userMap.get("accountNumber").toString();
        Page<Transaction> page = transactionService.findByAccount(pageNo,pageSize,accountNumber);
        List<Transaction> list = page.getContent();
        return list;
    }

    @GetMapping("history/description/{pageNo}")
    public List<Transaction> getTransactionByDescription(@PathVariable(value = "pageNo") int pageNo, @RequestBody Map<String, Object> userMap){
        int pageSize = 10;
        String description = userMap.get("description").toString();
        Page<Transaction> page = transactionService.findByDescription(pageNo,pageSize,description);
        List<Transaction> list = page.getContent();
        return list;
    }


}
