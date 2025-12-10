import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    public  List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        DBConnection db = new DBConnection();
        db.connect();
        String sql="select id, name  from Product_category";
        try(PreparedStatement ps=db.getConnection().prepareStatement(sql);
            ResultSet rs= ps.executeQuery()){
    while(rs.next()){
        Category category= new Category(rs.getInt("id"),rs.getString("name"));
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        categories.add(category);
    }
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
return categories;
    }
    public List<Product> getAllProducts(int page, int size) throws SQLException {
        DBConnection db = new DBConnection();
        db.connect();
        if (page<1) page=1;
        if (size<1)  size =20;
        if (size>60) size=30;
        List <Product>  productList = new ArrayList<>();
        int offset = (page-1)*size;
        String sql="select p.id ,p.name, p.price,p.creation_datetime,c.id AS category_id ,c.name AS category_name from Product p LEFT JOIN Product_category c ON p.id= c.product_id ORDER BY p.id LIMIT ? OFFSET ?";
        try (PreparedStatement prs= db.getConnection().prepareStatement(sql)){
           prs.setInt(1,size);
           prs.setInt(2,offset);
                try(ResultSet result= prs.executeQuery()){
                    while (result.next()) {
                        Product product= new Product(result.getInt("id"),result.getString("name"),result.getFloat("price"),result.getTimestamp("creation_datetime").toInstant(),new Category(result.getInt("category_id"),result.getString("category_name")));
                        product.setId(result.getInt("id"));
                        product.setName(result.getString("name"));
                        product.setPrice(result.getFloat("price"));
                        product.setCreationDatetime(result.getTimestamp("creation_datetime").toInstant());
                        product.setCategory(new Category(result.getInt("category_id"),result.getString("category_name")));
                        productList.add(product);
                    }

                }
                catch(SQLException sqlex){
                    sqlex.printStackTrace();
                }
        }



       return productList;
    }
public  List<Product>getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax) throws SQLException {
       DBConnection db = new DBConnection();
       db.connect();
       /*
    Instant creationMin = null;
    Instant creationMax = null;

    if (creationMinStr != null) {
        creationMin = Instant.parse(creationMinStr + "T00:00:00Z");
    }

    if (creationMaxStr != null) {
        creationMax = Instant.parse(creationMaxStr + "T23:59:59Z");
    }*/
        List<Product> filteredProductList = new ArrayList<>();
            String sql="Select p.id,p.name,p.price,p.creation_datetime,c.id as category_id ,c.name as category_name from Product p inner join Product_category c on p.id=c.product_id ";
            if (productName!=null ) sql+=" and p.name Ilike ?";
            if(categoryName !=null ) sql+=" and c.name Ilike ?";
            if (creationMin!=null) sql+=" and creation_datetime >= ?";
            if (creationMax!=null) sql+=" and creation_datetime <= ?";
            try(PreparedStatement stmt=db.getConnection().prepareStatement(sql)){
                int index=1;
                if (productName!=null) stmt.setString(index++, "%" +productName +"%");
                if (categoryName!=null) stmt.setString(index++, "%" +categoryName +"%");
                if(creationMin !=null) stmt.setTimestamp(index++, Timestamp.from(creationMin));
                if(creationMax !=null) stmt.setTimestamp(index++, Timestamp.from(creationMax));

                ResultSet rs=stmt.executeQuery();

                while (rs.next()) {
                    Product product= new Product(rs.getInt("id"),rs.getString("name"),rs.getFloat("price"),rs.getTimestamp("creation_datetime").toInstant(),new Category(index, rs.getString("category_name")));
                    product.setId(rs.getInt("id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getFloat("price"));
                    product.setCreationDatetime(rs.getTimestamp("creation_datetime").toInstant());
                    product.setCategory(new Category(rs.getInt("category_id"), rs.getString("category_name")));
                    filteredProductList.add(product);
                }

                }
            catch(SQLException  e){
                e.printStackTrace();
            }
            return  filteredProductList;
}
public  List<Product> getProductsByCriteria(String productName, String categoryName, Instant creationMin, Instant creationMax, int page , int size) throws SQLException {
        List<Product> filteredProductList = getProductsByCriteria(productName,categoryName,creationMin,creationMax);
    int firstIndex=(page-1)*size;
    int endIndex=Math.min(firstIndex+size,filteredProductList.size());
    if (firstIndex>=filteredProductList.size()) { return new ArrayList<>();}
    return filteredProductList.subList(firstIndex,endIndex);

}
}
