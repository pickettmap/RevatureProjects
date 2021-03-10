package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Payment;
import com.intellijeep.util.IntelliJeepArrayList;

import javax.xml.transform.Result;
import java.sql.*;

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
        int key = -1;

        String query = "insert into payment (customer_id, car_id, monthly_amount, loan_amount, payment_term, payment_remaining)\n" +
                "values (?,?,?,?,?,?)";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,payment.getCustomerID());
            ps.setInt(2,payment.getCarID());
            ps.setDouble(3,payment.getMonthlyAmount());
            ps.setDouble(4,payment.getLoanAmount());
            ps.setInt(5,payment.getPaymentTerm());
            ps.setInt(6, payment.getPaymentRemaining());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) {
                key = rs.getInt(1);
            }
            ps.close();
            return key;

        } catch (SQLException e) {
            e.printStackTrace();
            return key;
        }
    }

    public IntelliJeepArrayList<Payment> viewCustomerPaymentAll(int customerID) {
        String query = "select * from payment where customer_id = ?";
        try (Connection conn = ConnectionUtil.getConnection("dev")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customerID);
            IntelliJeepArrayList<Payment> payments = new IntelliJeepArrayList<Payment>(Payment.class,0);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("customer_id"),
                        rs.getInt("car_id"),
                        rs.getDouble("monthly_amount"),
                        rs.getDouble("loan_amount"),
                        rs.getInt("payment_term"),
                        rs.getInt("payment_remaining")
                );

                payments.add(payment);
            }
            return payments;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Payment getByID(Integer id) {
        return null;
    }

    public int getRemainingPaymentByCarID(Integer customerID, Integer carID) {
        int paymentRemaining = -1;
        String query = "select payment_remaining from payment where\n" +
                "customer_id = ? and car_id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customerID);
            ps.setInt(2,carID);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                paymentRemaining=  rs.getInt("payment_remaining");
            }
            return paymentRemaining;
        } catch (SQLException e) {
            e.printStackTrace();
            return paymentRemaining;
        }
    }

    @Override
    public IntelliJeepArrayList<Payment> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
    }

    public int getPaymentTermByCarID(Integer customerID, Integer carID) {
        int paymentTerm = -1;
        String query = "select payment_term from payment where\n" +
                "customer_id = ? and car_id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customerID);
            ps.setInt(2,carID);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                paymentTerm=  rs.getInt("payment_remaining");
            }
            return paymentTerm;
        } catch (SQLException e) {
            e.printStackTrace();
            return paymentTerm;
        }
    }

    @Override
    public Boolean update(Payment payment) {
        String query = "update payment set payment_remaining = ? where customer_id = ? and car_id = ?";

        try(Connection conn = ConnectionUtil.getConnection("dev")) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, payment.getPaymentRemaining());
            ps.setInt(2,payment.getCustomerID());
            ps.setInt(3,payment.getCarID());

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Payment> collection) {
        return 0;
    }
}
