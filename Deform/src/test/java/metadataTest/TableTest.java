package metadataTest;

import org.deform.metadata.ForeignKeySchema;
import org.deform.metadata.TableSchema;
import org.deform.model.Person;
import org.deform.model.Pet;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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
