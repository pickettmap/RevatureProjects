package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
import com.intellijeep.model.User;
import com.intellijeep.model.info.CarSaleInfo;
import com.intellijeep.model.info.CarSpecInfo;
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

            ps.setInt(1,car.getCarSaleInfo().getCarStatus().ordinal());
            ps.setString(2,car.getCarSpecInfo().getModel());
            ps.setInt(3, car.getCarSpecInfo().getModelYear());

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
                CarSpecInfo carSpecInfo = new CarSpecInfo(
                        rs.getString("model"),
                        rs.getInt("model_year")
                );
                CarSaleInfo carSaleInfo = new CarSaleInfo(
                        rs.getInt("id"),
                        CarStatus.convert(rs.getInt("status"))
                );
                Car c = new Car.CarBuilder()
                        .carSpecInfo(carSpecInfo)
                        .carSaleInfo(carSaleInfo)
                        .build();
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public Car getCustomerCars(Integer id) {
//        String query = "Select * from customer_car where id = ?";
//        try (Connection conn = ConnectionUtil.getConnection("dev")) {
//            PreparedStatement ps = conn.prepareStatement(query);
//            ps.setInt(1, id);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public IntelliJeepArrayList<Car> getByStatus(CarStatus status) {
        IntelliJeepArrayList<Car> carCollection = new IntelliJeepArrayList<>(Car.class,0);
        String query = "Select * from car where status = ?";
        try(Connection conn = ConnectionUtil.getConnection("dev")) {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1,status.ordinal());

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                CarSpecInfo carSpecInfo = new CarSpecInfo(
                    rs.getString("model"),
                    rs.getInt("model_year")
                );
                CarSaleInfo carSaleInfo = new CarSaleInfo(
                    rs.getInt("id"),
                    CarStatus.convert(rs.getInt("status"))
                );
                Car c = new Car.CarBuilder()
                        .carSaleInfo(carSaleInfo)
                        .carSpecInfo(carSpecInfo)
                        .build();
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
            while(rs.next()) {
                CarSpecInfo carSpecInfo = new CarSpecInfo(
                        rs.getString("model"),
                        rs.getInt("model_year")
                );
                CarSaleInfo carSaleInfo = new CarSaleInfo(
                        rs.getInt("id"),
                        CarStatus.convert(rs.getInt("status"))
                );
                Car c = new Car.CarBuilder()
                        .carSaleInfo(carSaleInfo)
                        .carSpecInfo(carSpecInfo)
                        .build();
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
        return null;
    }

    @Override
    public int updateAll(IntelliJeepArrayList<Car> collection) {
        return 0;
    }
}
