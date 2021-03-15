import com.enterprise.annotations.TestMethod;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.User;
import com.intellijeep.services.UserService;

@com.enterprise.annotations.TestClass
public class projectTestClass {
    public projectTestClass(){}

    UserService us = new UserService();

    @TestMethod(name = "test1", expected = "admin")
    public String testMethod1() {
        try {
            return us.findUserByUsername("admin").getUsername();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
