package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Car;
import com.intellijeep.model.Offer;
import com.intellijeep.util.IntelliJeepArrayList;

import java.sql.*;

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
        String query = "insert into offer (status, car_id, customer_id, amount) values (?, ?, ?, ?)";
        int key = -1;
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,offer.getOfferStatus().ordinal());
            ps.setInt(2,offer.getCarID());
            ps.setInt(3,offer.getCustomerID());
            ps.setInt(4,offer.getAmount());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()) {
                key = rs.getInt(1);
            }
            //TODO: Add the other close statements
            ps.close();
            return key;
        } catch (SQLException e) {
            e.printStackTrace();
            return key;
        }
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
        String query = "update offer set status = ? where id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,offer.getOfferStatus().ordinal());
            ps.setInt(2,offer.getOfferID());

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean updateOtherOffers(Car c) {
        String query = "update offer set status = 3 where status = 0 and car_id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,c.getCarSaleInfo().getCarID());

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Offer> collection) {
        return 0;
    }
}
