package com.fin.eteller.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String account;
    private Double amount;
    private String description;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.TIME)
    private Date time;
    private String customer;

    public Transaction(String accountNumber, Double amount, String desc, String date, String time, String customer) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        this.account = accountNumber;
        this.amount = amount;
        this.description = desc;
        this.date = dateFormat.parse(date);
        this.time = timeFormat.parse(time);
        this.customer = customer;
    }
}
