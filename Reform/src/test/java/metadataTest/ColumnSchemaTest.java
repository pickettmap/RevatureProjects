package metadataTest;

import org.reform.metadata.ColumnData;
import org.reform.model.Pet;
import org.junit.Assert;
import org.junit.Test;

public class ColumnSchemaTest {

    @Test
    public void columnConstructorTest() {
        Pet p = new Pet("Duke", "dog");

        ColumnData c1 = new ColumnData("Pet",p.getClass());

        Assert.assertEquals(c1.getName(),"Pet");
        Assert.assertEquals(c1.getType(),p.getClass());
    }
}
