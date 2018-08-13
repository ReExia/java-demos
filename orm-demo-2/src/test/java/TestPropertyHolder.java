import com.example.core.config.holder.PropertyHolder;
import com.example.core.config.manager.ProPertyHolderManager;
import org.junit.Test;

public class TestPropertyHolder {

    @Test
    public void test1(){
        PropertyHolder propertyHolder = new PropertyHolder();
        System.out.println(propertyHolder.getPropertyByKey("jdbc.driver_class"));

        PropertyHolder instance = ProPertyHolderManager.getInstance();
        System.out.println(instance.toString());
        System.out.println(instance.toString());
        System.out.println(instance.toString());
    }

    @Test
    public void test2(){
        for (int i = 0 ; i < 1000 ; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    PropertyHolder instance = ProPertyHolderManager.getInstance();
                    System.out.println(instance.toString());
                }
            }).start();
        }
    }

}
