import iocFactory.ApplicationContext;
import iocFactory.impl.ClassPathXMLApplicationContext;
import service.StudentService;

public class Test {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXMLApplicationContext("applicationContext.xml");
        StudentService stuServ = (StudentService) context.getBean("StudentService");
        System.out.println(stuServ.toString());
    }

}
