package connectionTest;

import org.checkerframework.checker.units.qual.C;
import org.junit.Assert;
import org.junit.Test;
import org.reform.connection.ConnectionSession;

import java.sql.Connection;
import java.sql.SQLException;

public class connectionSessionTest {

    ConnectionSession cs = new ConnectionSession();

    @Test
    public void getActiveConnectionTest() throws SQLException {
        Connection conn = cs.getActiveConnection();

        Assert.assertTrue(conn.isValid(100));
    }
}
