package org.reform.gateway;

import org.reform.QueryBuilder.DML.InsertQuery;
import org.reform.connection.ConnectionSession;
import org.reform.dto.DatabaseMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class DataGateway<T> {
    private DatabaseMapper dbm = new DatabaseMapper();
    private ConnectionSession cs = new ConnectionSession();

    public void createAllTables() {
        HashSet<String> queries = dbm.createTableQueries();

        try(Connection conn = cs.getActiveConnection()) {
            for(String s : queries) {
                PreparedStatement ps = conn.prepareStatement(s);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertIntoTable(T t) {
        InsertQuery iq = new InsertQuery(t);
        String sql = iq.generalSqlStatement();

        try(Connection conn = cs.getActiveConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps = iq.setPreparedStatement(ps);

            ps.executeUpdate();
        } catch (SQLException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
    }
}
