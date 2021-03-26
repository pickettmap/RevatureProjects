import org.junit.Test;
import org.reform.dto.DatabaseMapper;
import org.reform.gateway.DataGateway;
import org.reform.model.Pet;

public class quickTestArea {

    //TODO: all this stuff goes into the manager :)

    @Test
    public void quickTestTwo() {
        DatabaseMapper dg = new DatabaseMapper();
        dg.createTableQueries();
    }

    @Test
    public void quickTest() {
        //this goes into the mapper :)
        DataGateway dg = new DataGateway();
        Pet p = new Pet("sdf","asdf");

        dg.insertIntoTable(p);
    }
}
