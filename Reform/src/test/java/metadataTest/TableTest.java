package metadataTest;

import org.reform.metadata.ForeignKeySchema;
import org.reform.metadata.TableSchema;
import org.reform.model.Person;
import org.reform.model.Pet;
import org.junit.Assert;
import org.junit.Test;

public class TableTest {

    @Test
    public void tableConstructorTest() {
        Class c = Pet.class;

        TableSchema t = new TableSchema(c);
        Assert.assertTrue(t != null);
        Assert.assertEquals(t.getTableName(),"pet");
    }

    @Test
    public void quickie() {
        Pet p = new Pet("Duke","Dog");
        TableSchema t = new TableSchema(p.getClass());
        ForeignKeySchema fk = new ForeignKeySchema(Person.class);
        System.out.println("Before foreign key: " +t);
        t.addForeignKey(fk);
        System.out.println("After foreign key: " + t);
    }
}
