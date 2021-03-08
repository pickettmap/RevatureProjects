package com.intellijeep.db;

import com.intellijeep.model.Offer;
import com.intellijeep.util.IntelliJeepArrayList;

public class OfferDao implements GenericDao <Offer> {
    private static OfferDao instance;

    private OfferDao(){}

    static OfferDao getInstance() {
        if(instance == null) {
            instance = new OfferDao();
        }

        return instance;
    }

    @Override
    public int save(Offer offer) {
        return 0;
    }

    @Override
    public Offer getByID(Integer id) {
        return null;
    }

    @Override
    public IntelliJeepArrayList<Offer> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    @Override
    public Boolean update(Offer offer) {
        return null;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Offer> collection) {
        return 0;
    }
}
