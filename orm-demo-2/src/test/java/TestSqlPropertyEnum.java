import com.example.core.config.constant.SqlPropertyEnum;
import org.junit.Test;

public class TestSqlPropertyEnum {

    @Test
    public void test1(){

        String username = SqlPropertyEnum.USER_NAME.getStringValue();

        String url = SqlPropertyEnum.URL.getStringValue();

        String password = SqlPropertyEnum.PASSWORD.getStringValue();

        String driver = SqlPropertyEnum.DRIVER_CLASS.getStringValue();

        System.out.println(username);
        System.out.println(url);
        System.out.println(password);
        System.out.println(driver);
    }

}
