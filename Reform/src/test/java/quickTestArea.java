import org.junit.Test;
import org.reform.QueryBuilder.DML.UpdateQuery;
import org.reform.QueryBuilder.GenericQuery;
import org.reform.QueryBuilder.GenericQueryFactory;
import org.reform.dto.DataMapper;
import org.reform.dto.DatabaseMapper;
import org.reform.gateway.DataGateway;
import org.reform.metadata.DatabaseSchema;
import org.reform.metadata.QueryType;
import org.reform.model.Pet;

public class quickTestArea {
    Pet p = new Pet("sdasdfaf","asdfdf");
    Pet p1 = new Pet("duke","dog");
    GenericQueryFactory gqf = GenericQueryFactory.getInstance();


    @Test
    public void quickCreateQueriesTest() {
        DatabaseMapper dg = new DatabaseMapper();
        dg.createTableQueries();
    }

    @Test
    public void quickInsertTest() {
        //this goes into the mapper :)
        DatabaseSchema db = new DatabaseSchema();
        DataGateway dg = new DataGateway();
        dg.createAllTables();
//        dg.executeGenericQuery(p1, QueryType.UPDATE,1);
    }

    @Test
    public void quickGenericUpdate() {
        UpdateQuery uq = new UpdateQuery(p);
        System.out.println(uq.genericStatement());
    }

    @Test
    public void quickUpdateTest() {

        UpdateQuery uq = new UpdateQuery(p1);

    }

    @Test
    public void help() throws Exception {
        Pet p2 = (Pet) new DataGateway().selectSingleObject(p1, 1);
        System.out.println(p2.getName());
    }

    @Test
    public void newTesT() {
        DataMapper dm = new DataMapper(p);
        dm.delete(3);
    }

    @Test
    public void asdf() {
        DataGateway dg = new DataGateway();
        GenericQuery gq = gqf.getGenericQuery(p,QueryType.SELECT);
        dg.initializeGenericQuery(p,gq);
    }
}
