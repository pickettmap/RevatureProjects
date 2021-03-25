package gatewayTest;

import org.junit.Test;
import org.reform.config.ConnectionUtil;
import org.reform.gateway.DatabaseGateway;
import org.reform.metadata.ColumnData;
import org.reform.metadata.TableSchema;
import org.reform.model.Pet;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseGatewayTest {

    @Test
    public void createTableTest() throws SQLException {
        Pet p = new Pet("b","biurd");
        TableSchema t = new TableSchema(p.getClass());

        DatabaseGateway dg = new DatabaseGateway();

        String s = dg.createTable(t);

        PreparedStatement ps = ConnectionUtil.getConnection("persist").prepareStatement(s);
        ps.executeUpdate();
    }
}
