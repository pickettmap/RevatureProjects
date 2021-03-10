package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.Offer;
import com.intellijeep.model.OfferStatus;
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

    public IntelliJeepArrayList<Offer> getAllCarOffers(int carID) {
        IntelliJeepArrayList<Offer> offers = new IntelliJeepArrayList<Offer>(Offer.class,0);
        String query = "select * from offer where car_id = ?";

        try(Connection conn = ConnectionUtil.getConnection("dev")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,carID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Offer o = new Offer(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("amount"),
                        OfferStatus.convert(rs.getInt("status")));
                offers.add(o);
            }

            return offers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IntelliJeepArrayList<Offer> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    //Changing offer's status for specific offer
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

    public Boolean updateCustomerOffer(int customerID, int carID, OfferStatus offerStatus) {
        String query = "update offer set status = ? where customer_id=? and car_id=?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, offerStatus.ordinal());
            ps.setInt(2,customerID);
            ps.setInt(3,carID);

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Offer findHighestOffer(Car c) {
        String query = "select * from offer where amount = (select max(amount) from offer o2" +
                "where car_id = ?)";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,c.getCarID());

            ResultSet rs = ps.executeQuery();
            Offer o = new Offer(
                    rs.getInt("id"),
                    rs.getInt("car_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("amount"),
                    OfferStatus.convert(rs.getInt("status")));
            return o;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Rejecting all other offers that weren't accepted for a car
    public Boolean rejectPendingOffers(int car_id) {
        String query = "update offer set status = 2 where status != 1 and car_id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,car_id);

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
