import com.example.core.jdbc.SqlBuilder;
import com.example.demo.domain.Book;
import org.junit.Test;

public class TestSqlBulider {

    @Test
    public void test1(){
        Book book = new Book();
        book.setBookid("2121");
        book.setBookName("a");
        book.setAuthor("张三");

        String sql = SqlBuilder.buildInsert(book);
        System.out.println(sql);
    }

}
