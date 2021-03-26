package gatewayTest;

import org.junit.Assert;
import org.junit.Test;
import org.reform.dto.DataMapper;
import org.reform.dto.DatabaseMapper;
import org.reform.model.Person;
import org.reform.model.Pet;

public class DataMapperTest {
    Pet p = new Pet("Rosie","Cat");
    Person me = new Person("Mik", 22, p);
    DataMapper dm = new DataMapper();

    @Test
    public void DataMapperConstructionTest() {
        Assert.assertTrue(dm != null);
    }

//    @Test
//    public void DataMapperOverloadedConstructionTest() {
//        DataMapper dm1 = new DataMapper(p);
//
//        Assert.assertTrue(dm1 != null);
//        Assert.assertEquals(dm1.getId(), -1);
//        Assert.assertEquals(dm1.getMappedClass(),Pet.class);
//    }
//
//    @Test
//    public void setObjectTest() {
//        dm.setObject(me);
//        Assert.assertEquals(dm.getMappedClass(),Person.class);
//    }

//    //TODO: foreign key relations?
//    @Test
//    public void saveTest() {
//        dm.setObject(p);
//        dm.save();
//        dm.finishSession();
//    }
//
//    @Test
//    public void getById() {
//        dm.setObject(p);
//        int id = dm.getId();
//        Pet p2 = (Pet) dm.getById(id);
//        dm.finishSession();
//
//        Assert.assertEquals(p2.getName(),"Rosie");
//    }
//
//    @Test
//    public void getIdTest() {
//        dm.setObject(p);
//        int id = dm.getId();
//        dm.finishSession();
//
//        Assert.assertTrue(id != -1);
//        Assert.assertTrue(id > 1);
//    }
//
//    @Test
//    public void updateTest() {
//        dm.setObject(p);
//        p.setName("Rosemary Salami");
//        dm.update();
//        dm.finishSession();
//    }
//
//    @Test
//    public void deleteTest() {
//        dm.setObject(p);
//        dm.delete();
//        dm.finishSession();
//    }
}
