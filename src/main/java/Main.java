import java.sql.SQLException;

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
        System.out.println("all products ");
    }
}
