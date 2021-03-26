package connectionTest;

import org.junit.Assert;
import org.junit.Test;
import org.reform.connection.ConnectionFactory;

import java.sql.Connection;

public class connectionFactoryTest {

    ConnectionFactory cf = ConnectionFactory.getInstance();

    @Test
    public void connectionFactorySingletonTest() {


        //lol idk how to test singletons??
        Assert.assertTrue(cf != null);
    }

    @Test
    public void getConnectionPoolTest() {
        Connection[] arr = cf.getConnectionPool();

        Assert.assertEquals(arr.length,ConnectionFactory.MAX_CONNECTIONS);
    }
}
