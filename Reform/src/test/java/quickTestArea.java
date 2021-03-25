import org.h2.command.dml.Insert;
import org.junit.Test;
import org.reform.QueryBuilder.DML.InsertQuery;
import org.reform.config.ConnectionUtil;
import org.reform.gateway.DatabaseGateway;
import org.reform.metadata.DatabaseSchema;
import org.reform.model.Person;
import org.reform.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class quickTestArea {


    @Test
    public void quickTestOne() throws SQLException, IllegalAccessException {
        Pet p1 = new Pet("b","as");
        Person p = new Person("bui",2,p1);

        InsertQuery iq = new InsertQuery(p);
        Connection conn = ConnectionUtil.getConnection("persist");
        String sql = iq.generalSqlStatement();
//        sql = "select * from pet";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps = iq.makePreparedStatement(ps);
        System.out.println(ps);
        ps.executeUpdate();

    }

    @Test
    public void quickTestTwo() {
        DatabaseGateway dg = new DatabaseGateway();
        dg.createAllTables();
    }
}
