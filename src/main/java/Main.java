import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataRetriever retriever = new DataRetriever();
        System.out.println("All categories");
        retriever.getAllCategories().forEach(System.out::println);
        System.out.println("all products page 1 and size 2");
        retriever.getAllProducts(1,2).forEach(System.out::println);
        System.out.println("all products page 1 and size 5");
        retriever.getAllProducts(1,5).forEach(System.out::println);
        System.out.println("all products page 1 and size 3");
        retriever.getAllProducts(1,3).forEach(System.out::println);
        System.out.println("all products page 2 and size 2");
        retriever.getAllProducts(2,2).forEach(System.out::println);
        System.out.println("find dell");
        retriever.getProductsByCriteria("dell",null,null,null)
                .forEach(System.out::println);
        System.out.println("find info");
        retriever.getProductsByCriteria(null,"info",null,null)
                .forEach(System.out::println);
        System.out.println("find iphone mobile");
        retriever.getProductsByCriteria("iphone","mobile",null,null)
                .forEach(System.out::println);
        System.out.println("find 2024-02-01 2024-03-01");
        Instant after1 = LocalDateTime.of(2024,2,1,2,2).toInstant(ZoneOffset.UTC);
        retriever.getProductsByCriteria(null,null,after1,null)
                .forEach(System.out::println);
        System.out.println("find samsung bureau");
        retriever.getProductsByCriteria("samsung","bureau",null,null)
                .forEach(System.out::println);
        System.out.println("find sony info");
        retriever.getProductsByCriteria("sony","informatique",null,null)
                .forEach(System.out::println);
        System.out.println("find audio 2024-01-01 2024-12-01");
        Instant after= LocalDateTime.of(2024,1,1,12,30).toInstant(ZoneOffset.UTC);
        retriever.getProductsByCriteria(null,"audio",after,null)
                .forEach(System.out::println);
        System.out.println("find all null");
        retriever.getProductsByCriteria(null,null,null,null)
                .forEach(System.out::println);
        System.out.println("find null 1 10");
        retriever.getProductsByCriteria(null,null,null,null,1 ,10)
                .forEach(System.out::println);
        System.out.println("find dell 1 5");
        retriever.getProductsByCriteria("dell",null,null,null,1,5)
                .forEach(System.out::println);
        System.out.println("find info 1 10");
        retriever.getProductsByCriteria(null,"informatique",null,null ,1 ,10)
                .forEach(System.out::println);
    }
}
