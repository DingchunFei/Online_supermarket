package com.fei.store.dao.daoImpl;

import com.fei.store.dao.CategoryMapper;
import com.fei.store.domain.Category;
import com.fei.store.utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapperImpl implements CategoryMapper {
    @Override
    public List<Category> getAllCats() throws Exception {
        List<Category> list = new ArrayList<>();

        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT * from t_category");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Category category = new Category();
                category.setCid(rs.getString(1));
                category.setCname(rs.getString(2));
                list.add(category);
                System.out.println(category);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

}
