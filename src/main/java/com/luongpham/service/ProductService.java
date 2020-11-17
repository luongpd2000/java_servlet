package com.luongpham.service;

import com.luongpham.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    List<Product> findAll() throws SQLException;

    Product findById(int id) throws SQLException;

    Product insert(Product product) throws SQLException;

    boolean update(Product product) throws SQLException;

    boolean delete(int id) throws SQLException;

    List<Product> sortByCreateDate() throws SQLException;

    List<Product> sortBy(String field, boolean isAsc) throws SQLException;

    List<Product> findByCategory(int idCategory) throws Exception;

    List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws Exception;
}
