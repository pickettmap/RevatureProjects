package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.User;
import com.intellijeep.util.IntelliJeepArrayList;

import java.sql.*;

public class CarDao implements GenericDao<Car>{

    public static CarDao instance;

    private CarDao(){}

    static CarDao getInstance(){
        if(instance==null) {
            instance = new CarDao();
        }

        return instance;
    }


    @Override
    public int save(Car car) {
        int key = -1;

        String query = "insert into car (status, model, model_year) values (?,?,?)";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,car.getCarStatus().ordinal());
            ps.setString(2,car.getModel());
            ps.setInt(3, car.getModelYear());

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

    public int saveIntoCustomerCar(Car c, int userid) {
        int key = -1;

        String query = "insert into customer_car (car_id, customer_id) values (?,?)";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,c.getCarID());
            ps.setInt(2,userid);

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

    @Override
    public Car getByID(Integer id) {
        String query = "Select * from car where id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return new Car(
                    rs.getInt("id"),
                    CarStatus.convert(rs.getInt("status")),
                    rs.getString("model"),
                    rs.getInt("model_year"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public IntelliJeepArrayList<Car> getByStatus(CarStatus status) {
        IntelliJeepArrayList<Car> carCollection = new IntelliJeepArrayList<>(Car.class,0);
        String query = "Select * from car where status = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,status.ordinal());

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Car c = new Car(
                        rs.getInt("id"),
                        CarStatus.convert(rs.getInt("status")),
                        rs.getString("model"),
                        rs.getInt("model_year"));
                carCollection.add(c);
            }

            return carCollection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IntelliJeepArrayList<Car> getOwnedCars(int customerID) {
        IntelliJeepArrayList<Car> carCollection = new IntelliJeepArrayList<>(Car.class,0);
        String query = "select * from car c\n" +
                "\tjoin customer_car cc on cc.car_id  = c.id\n" +
                "\tjoin app_user au on cc.customer_id = au.id \n" +
                "\twhere au.id = ?" ;

        try(Connection conn = ConnectionUtil.getConnection("dev")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,customerID);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Car c = new Car(
                        rs.getInt("id"),
                        CarStatus.convert(rs.getInt("status")),
                        rs.getString("model"),
                        rs.getInt("model_year"));
                carCollection.add(c);
            }

            return carCollection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IntelliJeepArrayList<Car> getAll() {
        return null;
    }

    public Boolean removeCarFromLot(Integer id) {
        String query = "update car set status = 1 where id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")){
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);

            if(ps.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean remove(Integer id) {
        String query = "delete from car where status != 2 and id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,id);

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
    public Boolean update(Car car) {
        String query = "update car set status = ? where id = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,car.getCarStatus().ordinal());
            ps.setInt(2,car.getCarID());

            if(ps.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Car> collection) {
        return 0;
    }
}
