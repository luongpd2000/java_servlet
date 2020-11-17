package com.luongpham.service_impl;

import com.luongpham.dao.ProductDao;
import com.luongpham.dao_impl.ProductDaoImpl;
import com.luongpham.model.Product;
import com.luongpham.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductService_Impl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> findAll() throws SQLException {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) throws SQLException {
        return id > 0 ? productDao.findById(id) : null;
    }

    @Override
    public Product insert(Product product) throws SQLException {
        return productDao.insert(product);
    }

    @Override
    public boolean update(Product product) throws SQLException {
        return productDao.update(product);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return id > 0 ? productDao.delete(id) : null;
    }

    @Override
    public List<Product> sortByCreateDate() throws SQLException {
        return productDao.sortByCreateDate();
    }

    @Override
    public List<Product> sortBy(String field, boolean isAsc) throws SQLException {
        return field != null ? productDao.sortBy(field, isAsc) : null;
    }

    @Override
    public List<Product> findByCategory(int idCategory) throws Exception {
        return idCategory > 0 ? productDao.findByCategory(idCategory) : null;
    }

    @Override
    public List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws Exception {
        return name != null ? productDao.search(name, startDate, endDate, soldOut, guarantee, category, bouth, promotion) : null;
    }
}
