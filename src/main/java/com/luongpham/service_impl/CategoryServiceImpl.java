package com.luongpham.service_impl;

import com.luongpham.dao.CategoryDao;
import com.luongpham.dao_impl.CategoryDaoImpl;
import com.luongpham.model.Category;
import com.luongpham.service.CategoryService;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() throws SQLException {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) throws SQLException {
//        Category category = null;
//        if(id > 0) category = categoryDao.findById(id);
//        return category;
        return id > 0 ? categoryDao.findById(id) : null;
    }

    @Override
    public Category insert(String name) throws SQLException {
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setDeleted(false);
        return categoryDao.insert(newCategory);
    }

    @Override
    public boolean update(Category category) throws SQLException {
        return category.getId() > 0 ? categoryDao.update(category) : false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return id > 0 ? categoryDao.delete(id) : false;
    }
}
