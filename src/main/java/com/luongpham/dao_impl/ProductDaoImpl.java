package com.luongpham.dao_impl;

import com.luongpham.dao.CategoryDao;
import com.luongpham.dao.ProductDao;
import com.luongpham.model.Category;
import com.luongpham.model.MyConnection;
import com.luongpham.model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDaoImpl implements ProductDao {

    MyConnection myConnection = new MyConnection();

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Product> sortByCreateDate() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "select * from product where deleted = false order by create_date DESC"; //DESC: giảm dần ASC: tân
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            do {
                Product product = getObject(resultSet);
                if (product != null) products.add(product);
            } while (resultSet.next());
        }
        return products;
    }

    @Override
    public List<Product> sortBy(String field, boolean isAsc) throws SQLException {
        String sql = "select * from product where " +
                "deleted = false order by " + field + (isAsc ? "ASC" : "DESC");
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> findByCategory(int idCategory) throws Exception {
        String sql = "select p.* from product as p, category as c " +
                "where c.deleted = false and c.id = ? and p.category_id = category.id";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, idCategory);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws Exception {
        String sql = "select distinct p.* from product as p, category as c where p.deleted = false and " +
                "p.name like ? and " +
                "(? is null or p.create_date >= ?) and " +
                "(? is null or p.create_date <= ?) and " +
                "(? is null or p.sold_out = ?) and " +
                "(? = -1 or p.guarantee = ?) and " +
                "(? = -1 or (c.deleted = false and c.id = ? and p.category_id = c.id))  and " +
                "(? = -1 or p.bought = ?) and" +
                "(? = -1 or p.promotion = ?)";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setString(1, "%" + name + "%");
        preparedStatement.setString(2, startDate);
        preparedStatement.setString(3, startDate == null ? "0000-01-01" : startDate);
        preparedStatement.setString(4, endDate);
        preparedStatement.setString(5, endDate == null ? "9999-12-31" : endDate);
        if (soldOut == null) {
            preparedStatement.setString(6, null);
            preparedStatement.setBoolean(7, true);
        } else {
            preparedStatement.setString(6, "");
            preparedStatement.setBoolean(7, soldOut);
        }
        preparedStatement.setInt(8, guarantee);
        preparedStatement.setInt(9, guarantee);
        preparedStatement.setInt(10, category);
        preparedStatement.setInt(11, category);
        preparedStatement.setInt(12, bouth);
        preparedStatement.setInt(13, bouth);
        preparedStatement.setInt(14, promotion);
        preparedStatement.setInt(15, promotion);
        ResultSet resultSet = preparedStatement.executeQuery();
        return getList(resultSet);
    }

    @Override
    public Product getObject(ResultSet resultSet) throws SQLException {
        Product product = null;
        product = new Product(resultSet.getInt("id"), resultSet.getString("name"),
                resultSet.getDouble("price"), resultSet.getDate("create_date"),
                resultSet.getBoolean("deleted"), resultSet.getString("image"),
                resultSet.getString("introduction"),
                resultSet.getString("specification"),
                resultSet.getBoolean("sold_out"), resultSet.getInt("guarantee"),
                resultSet.getInt("category_id"), resultSet.getInt("bought"),
                resultSet.getInt("promotion"));
        return product;
    }

    @Override
    public List<Product> getList(ResultSet resultSet) throws SQLException {
        List<Product> productList = new ArrayList<>();
        if (resultSet.first()) {
            do {
                Product product = getObject(resultSet);
                if (product != null) productList.add(product);
            } while (resultSet.next());
        }
        return productList;
    }

    @Override
    public List<Product> findAll() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from product where deleted = false"; //câu lệnh sql cần thực hiện để find all
        PreparedStatement preparedStatement = myConnection.prepare(sql); // lấy ra prepare dùng cho câu lệnh query
        ResultSet resultSet = preparedStatement.executeQuery(); // thực thi câu lệnh query và lấy resultSet trả về
        //resetSet.first() để đưa con trỏ resetSet về bản ghi đầu tiên lấy được nếu tồn tại trả về true, còn không thì false
//        if(resultSet.first()) {
//            do {
//                Product product = getObject(resultSet);
//                if(product != null) productList.add(product);
//            } while(resultSet.next()); //.next() đưa con trỏ resultSet đến dòng kết tiếu nếu tồn tại trả về true, còn không thì false
//        }
        return getList(resultSet);
    }

    @Override
    public Product findById(int id) throws SQLException {
        Product product = null;
        String sql = "select * from product where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id); // dùng để set giá trị vào index chấm hỏi tương ứng từ 1
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            product = getObject(resultSet);
        }
        return product;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        Product newProduct = null;
        String sql = "insert into product values (null, ?, ?, ?, false, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setDate(3, new Date(new java.util.Date().getTime()));
        preparedStatement.setString(4, product.getImage());
        preparedStatement.setString(5, product.getIntroduction());
        preparedStatement.setString(6, product.getSpecification());
        preparedStatement.setBoolean(7, product.isSoldOut());
        preparedStatement.setInt(8, product.getGuarantee());
        preparedStatement.setInt(9, product.getCategoryId());
        preparedStatement.setInt(10, product.getBouth());
        preparedStatement.setInt(11, product.getPromotion());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) {
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.first()) {
                newProduct = findById((int) resultSet.getLong(1));
            }
        }
        return newProduct;
    }

    @Override
    public boolean update(Product product) throws SQLException {
        boolean result = false;
        String sql = "update product set name = ? , price = ? ,  deleted = ? , image = ? , introduction = ? ," +
                " specification = ?, soldOut = ? , guarantee = ? ,categoryId = ?,bought = ?, promotion = ?  where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setBoolean(3, product.isDeleted());
        preparedStatement.setString(4, product.getImage());
        preparedStatement.setString(5, product.getIntroduction());
        preparedStatement.setString(6, product.getSpecification());
        preparedStatement.setBoolean(7, product.isSoldOut());
        preparedStatement.setInt(8, product.getGuarantee());
        preparedStatement.setInt(9, product.getCategoryId());
        preparedStatement.setInt(10, product.getBouth());
        preparedStatement.setInt(11, product.getPromotion());
        preparedStatement.setInt(12, product.getId());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "update product set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }
}
