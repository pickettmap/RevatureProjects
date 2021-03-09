package com.intellijeep.db;

import com.intellijeep.config.ConnectionUtil;
import com.intellijeep.model.Car;
import com.intellijeep.model.CarStatus;
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

    @Override
    public IntelliJeepArrayList<Car> getAll() {
        return null;
    }

    @Override
    public Boolean remove(Integer id) {
        return null;
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
