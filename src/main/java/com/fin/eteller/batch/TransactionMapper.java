package com.fin.eteller.batch;

import com.fin.eteller.entity.Transaction;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransactionMapper implements FieldSetMapper<Transaction> {
    @Override
    public Transaction mapFieldSet(FieldSet fieldSet) throws BindException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        Transaction t = new Transaction();
        t.setAccount(fieldSet.readString(0));
        t.setAmount(fieldSet.readDouble(1));
        t.setDescription(fieldSet.readString(2));
        try {
            t.setDate(dateFormat.parse(fieldSet.readString(3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            t.setTime(timeFormat.parse(fieldSet.readString(4)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        t.setCustomer(fieldSet.readString(5));
        return t;
    }
}
