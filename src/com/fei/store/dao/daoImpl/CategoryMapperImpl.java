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

	@Override
	public Integer getCatsDependencies(Category cate) {

        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT count(*) from t_product WHERE cid=?");
			stmt.setString(1, cate.getCid());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
	}

	@Override
	public void addCategory(Category category) {
		try {
			PreparedStatement stmt = DBConnection.prepare("INSERT into t_category values(?,?)");
			stmt.setString(1, category.getCid());
			stmt.setString(2, category.getCname());
			stmt.executeUpdate();
			DBConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delCategory(String cid) {
		try {
			PreparedStatement stmt = DBConnection.prepare("DELETE FROM t_category WHERE cid=?");

			stmt.setString(1, cid);

			int rs=stmt.executeUpdate();
			DBConnection.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void editCategory(Category category) {
		try {
			PreparedStatement stmt = DBConnection.prepare("UPDATE t_category SET cname=? WHERE cid=?");
			
			stmt.setString(1, category.getCname());
			stmt.setString(2, category.getCid());
			
			stmt.executeUpdate();
			DBConnection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
