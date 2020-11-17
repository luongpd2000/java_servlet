package com.luongpham.dao_impl;

import com.luongpham.dao.CategoryDao;
import com.luongpham.model.Category;
import com.luongpham.model.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private MyConnection myConnection = new MyConnection();

    //hàm dùng để đọc ra các trường trong resultSet để trả về đối tượng có các thuộc tính tương ứng
    @Override
    public Category getObject(ResultSet resultSet) throws SQLException {
        Category category = null;
        // Sử dụng contructor full tham số (int id , String name,boolean deleleted)
        //resultSet.getInt("tên cột") để lấy ra giá trị của tên cột tương ứng ví dụ resultSet.getInt("id") để lấy ra
        //giá trị cột id
        category = new Category(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getBoolean("deleted"));
        return category;
    }

    @Override
    public List<Category> getList(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    public List<Category> findAll() throws SQLException {
        List<Category> categoryList = new ArrayList<>();
        //select *(là lấy tất cả các trường) from category(là thực hiện với bảng nào) where deleted = false (chứa các điều kiện khi lấy)
        String sql = "select * from category where deleted = false"; //câu lệnh sql cần thực hiện để find all
        PreparedStatement preparedStatement = myConnection.prepare(sql); // lấy ra prepare dùng cho câu lệnh query
        ResultSet resultSet = preparedStatement.executeQuery(); // thực thi câu lệnh query và lấy resultSet trả về
        //resetSet.first() để đưa con trỏ resetSet về bản ghi đầu tiên lấy được nếu tồn tại trả về true, còn không thì false
        if (resultSet.first()) {
            do {
                Category category = getObject(resultSet);
                if (category != null) categoryList.add(category);
            } while (resultSet.next()); //.next() đưa con trỏ resultSet đến dòng kết tiếu nếu tồn tại trả về true, còn không thì false
        }
        return categoryList;
    }

    @Override
    public Category findById(int id) throws SQLException {
        Category category = null;
        String sql = "select * from category where deleted = false and id = ?";
        PreparedStatement preparedStatement = myConnection.prepare(sql);
        preparedStatement.setInt(1, id); // dùng để set giá trị vào index chấm hỏi tương ứng từ 1
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.first()) {
            category = getObject(resultSet);
        }
        return category;
    }

    @Override
    public Category insert(Category category) throws SQLException {
        Category newCategory = null;
        String sql = "insert into category (name, deleted) values (?,?)";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql); // lấy ra prepare dùng để thực thi câu lệnh update
        preparedStatement.setString(1, category.getName());
        preparedStatement.setBoolean(2, category.isDeleted());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) { //nếu insert thành công thì thực hiện lấy id vừa insert
            ResultSet resultSet = preparedStatement.getGeneratedKeys(); // hàm dùng để lấy id cho bản ghi vừa insert
            if (resultSet.first()) { // nếu có
                newCategory = findById((int) resultSet.getLong(1)); // lấy id bằng cách resultSet.getLong(1) kiểu trả về cột 1 của reusltSet là long,
                // sau đó dùng hàm findById((int) key) vì key kiểu long nên ép kiểu về int để tìm lại bản ghi vừa insert;
            }
        }
        return newCategory;
    }

    @Override
    public boolean update(Category category) throws SQLException {
        boolean result = false;
        String sql = "update category set name = ? where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setString(1, category.getName());
        preparedStatement.setInt(2, category.getId());
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        boolean result = false;
        String sql = "update category set deleted = true where id = ?";
        PreparedStatement preparedStatement = myConnection.prepareUpdate(sql);
        preparedStatement.setInt(1, id);
        int rs = preparedStatement.executeUpdate();
        if (rs > 0) result = true;
        return result;
    }
}
