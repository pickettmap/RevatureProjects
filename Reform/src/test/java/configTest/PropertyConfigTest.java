package configTest;

import org.junit.Assert;
import org.junit.Test;
import org.reform.connection.PropertyConfig;

public class PropertyConfigTest {

    PropertyConfig pc = new PropertyConfig();

    @Test
    public void PropertyConfigConstructorTest() {
        Assert.assertTrue(!pc.equals(null));
    }

    @Test
    public void configurePropertiesTest() {
        Assert.assertEquals(pc.getUrl(),"jdbc:postgresql://project0.cmopccrkolht.us-west-1.rds.amazonaws.com:5432/postgres?currentSchema=cool_stuff");
        Assert.assertEquals(pc.getUsername(),"mikayla");
        Assert.assertEquals(pc.getPassword(),"password");
    }

    @Test
    public void setProfileTest() {
        pc.setProfile("persist");
        Assert.assertEquals(pc.getProfile(),"persist");
    }
}
