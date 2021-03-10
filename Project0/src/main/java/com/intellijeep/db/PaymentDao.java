package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Offer;
import com.intellijeep.model.OfferStatus;
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
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("car_id"),
                        rs.getDouble("monthly_amount"),
                        rs.getDouble("loan_amount"),
                        rs.getInt("payment_term"),
                        rs.getInt("payment_remaining"),
                        rs.getDouble("loan_balance")
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
        String query = "Select * from payment where id = ?";
        try (Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("car_id"),
                        rs.getDouble("monthly_amount"),
                        rs.getDouble("loan_amount"),
                        rs.getInt("payment_term"),
                        rs.getInt("payment_remaining"),
                        rs.getDouble("loan_balance")
                );
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }


    public Payment getByCarCustomer(int carID, int customerID) {
        String query = "Select * from payment where car_id = ? and customer_id = ?";
        try (Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, carID);
            ps.setInt(2,customerID);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Payment payment = new Payment(
                        rs.getInt("id"),
                        rs.getInt("customer_id"),
                        rs.getInt("car_id"),
                        rs.getDouble("monthly_amount"),
                        rs.getDouble("loan_amount"),
                        rs.getInt("payment_term"),
                        rs.getInt("payment_remaining"),
                        rs.getDouble("loan_balance")
                );
                return payment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
                paymentTerm=  rs.getInt("payment_term");
            }
            return paymentTerm;
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return paymentTerm;
    }

    public Boolean updateAfterPayment(Payment payment) {
        String query = "update payment set payment_remaining = ?, loan_balance = ? where id = ?;";

        try(Connection conn = ConnectionUtil.getConnection("dev")) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,payment.getPaymentRemaining());
            ps.setDouble(2, payment.getLoanBalance());
            ps.setInt(3, payment.getPaymentID());

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Boolean update(Payment payment) {
        String query = "update payment set monthly_amount = ?, loan_amount = ?, payment_term = ?, payment_remaining = ?, loan_balance = ? where customer_id = ? and car_id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1,payment.getMonthlyAmount());
            ps.setDouble(2, payment.getLoanAmount());
            ps.setInt(3,payment.getPaymentTerm());
            ps.setInt(4,payment.getPaymentRemaining());
            ps.setDouble(5,payment.getLoanBalance());
            ps.setInt(6,payment.getCustomerID());
            ps.setInt(7,payment.getCarID());


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
