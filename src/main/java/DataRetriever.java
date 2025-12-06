import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public  List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        DBConnection db = new DBConnection();
        db.connect();
        String sqlreq="select id, name  from category";
        try(PreparedStatement ps=db.getConnection().prepareStatement(sqlreq);
            ResultSet rs= ps.executeQuery()){
    while(rs.next()){
        Category category= new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        categories.add(category);
    }
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
return categories;
    }
    public List<Product> getAllProducts(int page, int size){
        DBConnection db = new DBConnection();
        db.connect();
        if (page<1) page=1;
        if (size<1) = size 20;
        if (size>60) size=30;
        List <Product>  productList = new ArrayList<>();
        int offset = (page-1)*size;
        String sql="select p.id ,p.name, p.price,p.creation_datetime,c.name AS category_name from Product p LEFT JOIN Product_category c ON p.id= c.product_id ORDER BY p.id LIMIT ? OFFSET ?";
        try(PreparedStatement prs=db.getConnection().prepareStatement(sql) {
            prs.setInt(1,size);
            prs.setInt(2,offset);
            try (ResultSet result=prs.executeQuery()){
                while (result.next()){
                    Product product= new Product();
                    product.setId(result.getInt("id"));
                    product.setName(result.getString )
                }
            }

        }
    }

}
