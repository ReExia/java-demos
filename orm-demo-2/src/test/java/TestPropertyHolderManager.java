import com.example.core.convertor.EntityToParamtersConvertor;
import com.example.demo.domain.Book;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TestPropertyHolderManager {

    @Test
    public void test1(){
        Book book = new Book();
        book.setBookid("shaushahsiuahi");
        book.setAuthor("张三");
        book.setBookName("语文");

        Map map = EntityToParamtersConvertor.convert(book);

        String idField = String.valueOf(map.get("idField"));
        String idValue = String.valueOf(map.get("idValue"));

        String table = String.valueOf(map.get("table"));
        List fieldList = (List) map.get("fieldList");
        List valueFieldList = (List) map.get("valueFieldList");

        System.out.println("test finished ... ");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    String uuid = UUID.randomUUID().toString();
                    Book book = new Book();
                    book.setBookid(uuid);
                    book.setAuthor("张" + uuid);
                    book.setBookName("语" + uuid);
                    Map convert = EntityToParamtersConvertor.convert(book);
                    String idValue = String.valueOf(convert.get("idValue"));
                    System.out.println(idValue);
                }
            }).start();
        }
    }

}
