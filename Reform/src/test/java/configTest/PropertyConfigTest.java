package configTest;

import org.junit.Assert;
import org.junit.Test;
import org.reform.config.PropertyConfig;

public class PropertyConfigTest {

    @Test
    public void configureProperties() {
        PropertyConfig pc = new PropertyConfig();

        pc.configureProperties("persist");

        Assert.assertEquals(pc.getUrl(),"jdbc:h2:tcp://localhost/~/test");

        Assert.assertEquals(pc.getUsername(),"sa");

        Assert.assertEquals(pc.getPassword(),"");
    }
}
