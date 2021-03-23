package configTest;

import org.junit.Test;
import org.reform.config.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtilTest {

    @Test
    public void getConnectionTest() throws SQLException
    {
        Connection c = ConnectionUtil.getConnection("mem");

        String sql = "create table ninja(name text);";
        String sql1 = "insert into NINJA values (?)";
        String sql2 = "select * from ninja";

        PreparedStatement stmt = c.prepareStatement(sql);
        stmt.executeUpdate(); // execute the create table query

        stmt = c.prepareStatement(sql1); // prepare the insert
        stmt.setString(1,"Naruto");
        stmt.executeUpdate(); // execute the insert

        stmt = c.prepareStatement(sql2); // prepare the select
        ResultSet rs = stmt.executeQuery(); // execute the select
        while(rs.next()){
            System.out.println(rs.getString("name"));
        }
    }
}
