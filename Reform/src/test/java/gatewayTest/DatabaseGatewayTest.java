package gatewayTest;

import org.junit.Test;
import org.reform.QueryBuilder.DDL.CreateQuery;
import org.reform.config.ConnectionUtil;
import org.reform.gateway.DatabaseGateway;
import org.reform.metadata.TableSchema;
import org.reform.model.Pet;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;

public class DatabaseGatewayTest {

    @Test
    public void createTableTest() throws SQLException {

        DatabaseGateway dg = new DatabaseGateway();
        HashSet<String> queries = dg.createAllTables();

        //this should go into the gateway
        for(String s : queries) {
            PreparedStatement ps = ConnectionUtil.getConnection("persist").prepareStatement(s);
            ps.executeUpdate();
        }
    }
}
