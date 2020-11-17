package com.luongpham.dao;

import com.luongpham.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao extends com.luongpham.dao.BaseDao<Product> {

    List<Product> sortByCreateDate() throws SQLException;

    List<Product> sortBy(String field, boolean isAsc) throws SQLException;

    List<Product> findByCategory(int idCategory) throws Exception;

    List<Product> search(String name, String startDate, String endDate, Boolean soldOut, int guarantee, int category, int bouth, int promotion) throws Exception;

}
