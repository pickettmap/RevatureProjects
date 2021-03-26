package org.reform.gateway;

import org.reform.QueryBuilder.DML.DeleteQuery;
import org.reform.QueryBuilder.DML.SelectQuery;
import org.reform.QueryBuilder.GenericQuery;
import org.reform.connection.ConnectionSession;
import org.reform.dto.DatabaseMapper;
import org.reform.util.PreparedStatementUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class DataGateway<T> {
    private ConnectionSession cs = new ConnectionSession();
    private String sql;
    PreparedStatementUtil psu;

    public void createAllTables() {
        HashSet<String> queries = DatabaseMapper.createTableQueries();

        try(Connection conn = cs.getActiveConnection()) {
            for(String s : queries) {
                PreparedStatement ps = conn.prepareStatement(s);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //used for insert
    public void initializeGenericQuery(T t, GenericQuery gq) {
        this.sql = gq.genericStatement();
        this.psu = new PreparedStatementUtil(t);
    }

    //used for update
    public void initializeGenericQuery(T t, GenericQuery gq, int id) {
        this.sql = gq.genericStatement();
        this.psu = new PreparedStatementUtil(t,id);
    }

    public void doSomething(){
        try(Connection conn = cs.getActiveConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps = psu.setPreparedStatement(ps);
            ps.executeUpdate();
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
    }


    //selecting single object
    public T selectSingleObject(T t, int id) {
        this.sql = new SelectQuery(t.getClass()).selectOneFromTable();
        this.psu = new PreparedStatementUtil(t, id);

        try(Connection conn = cs.getActiveConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            Class[] parameters = new Class[rsmd.getColumnCount()];
            List<Object> values = new ArrayList<>();
            while(rs.next()) {
                for(int i = 1; i <= rsmd.getColumnCount(); i++){
                    parameters[i-1] = rs.getObject(i).getClass();
                    values.add(rs.getObject(i));
                }

                Class[] modifiedArray = Arrays.copyOfRange(parameters, 1, parameters.length);
                values.remove(0);

                Constructor constructor = t.getClass().getConstructor(modifiedArray);
                return (T) constructor.newInstance(values.toArray());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //finding id by values
    public int getIdByObjectValues(T t){
        sql = new SelectQuery(t).genericStatement();
        try(Connection conn = cs.getActiveConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps = psu.setPreparedStatement(ps);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }

        return -1;
    }

    public void deleteObject(T t, int id) {
        this.sql = new DeleteQuery(t).genericStatement();
        try(Connection conn = cs.getActiveConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
