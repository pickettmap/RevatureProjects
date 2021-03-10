import com.enterprise.model.MetaTestData;
import com.enterprise.util.HashMap;
import com.enterprise.util.TestDiscovery;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.User;

import java.lang.reflect.Method;

public class Driver {
    public static void main(String[] args) {
        HashMap<Method, MetaTestData> resultmap = null;
        try {
            resultmap = new TestDiscovery().runAndStoreTestInformation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            System.out.println(resultmap);
        }
    }
}
