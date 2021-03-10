import com.enterprise.annotations.TestMethod;
import com.intellijeep.db.DaoFactory;
import com.intellijeep.db.UserDao;
import com.intellijeep.model.User;

@com.enterprise.annotations.TestClass
public class projectTestClass {
    @TestMethod(name = "test1", expected = "admin")
    public String testMethod1() {
        projectTestClass p = new projectTestClass();
        UserDao u = (UserDao) DaoFactory.createDao(User.class);
        User user = u.getByUsername("admin");
        return user.getUsername();
    }

}
