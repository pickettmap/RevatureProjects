package com.intellijeep.db;

import com.intellijeep.model.Payment;
import com.intellijeep.util.IntelliJeepArrayList;

public class PaymentDao implements GenericDao <Payment> {
    private static PaymentDao instance;

    private PaymentDao(){}

    static PaymentDao getInstance() {
        if(instance == null) {
            instance = new PaymentDao();
        }

        return instance;
    }

    @Override
    public int save(Payment payment) {
        return 0;
    }

    @Override
    public Payment getByID(Integer id) {
        return null;
    }

    @Override
    public IntelliJeepArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Payment payment) {
        return null;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Payment> collection) {
        return 0;
    }
}
