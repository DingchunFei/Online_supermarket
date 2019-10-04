package com.fei.store.dao.daoImpl;

import com.fei.store.dao.ProductMapper;
import com.fei.store.domain.Category;
import com.fei.store.domain.Description;
import com.fei.store.domain.Product;
import com.fei.store.domain.User;
import com.fei.store.utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductMapperImpl implements ProductMapper {
    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT * from t_product");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getString(1));
                product.setPname(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setPimage(rs.getString(4));
                product.setCid(rs.getString(5));
                
                list.add(product);
                System.out.println(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product findProductByPid(String pid) throws Exception {
        Product product = new Product();
        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT * from t_product where pid=?");

            stmt.setString(1, pid);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                product.setPid(rs.getString(1));
                product.setPname(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setPimage(rs.getString(4));
                product.setCid(rs.getString(5));
                
                //Embedded Value
                Description desc = new Description();
                desc.setDescription(rs.getString(6));
                product.setPdesc(desc);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return product;
    }


    @Override
    public List<Product> findProductsByCid(String cid) throws Exception {
        List<Product> list = new ArrayList<>();
        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT * from t_product where cid=?");

            stmt.setString(1, cid);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                Product product = new Product();
                product.setPid(rs.getString(1));
                product.setPname(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setPimage(rs.getString(4));
                product.setCid(rs.getString(5));
                list.add(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> getProductsByName(String pname) throws Exception {
        List<Product> list = new ArrayList<>();

        try {
            PreparedStatement stmt = DBConnection.prepare("SELECT * from t_product WHERE pname LIKE ?");
            stmt.setString(1, "%"+pname+"%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getString(1));
                product.setPname(rs.getString(2));
                product.setPrice(rs.getDouble(3));
                product.setPimage(rs.getString(4));
                product.setCid(rs.getString(5));
                list.add(product);
                System.out.println(product);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

	@Override
	public int insertProduct(Product product) throws Exception {
		try {
			PreparedStatement stmt = DBConnection.prepare("INSERT into t_product values(?,?,?,?,?,?)");
			stmt.setString(1, product.getPid());
			stmt.setString(2, product.getPname());
			stmt.setDouble(3, product.getPrice());
			stmt.setString(4, product.getPimage());
			stmt.setString(5, product.getCid());
			stmt.setString(6, product.getPdesc().getDescription());
            
			int rs=stmt.executeUpdate();
			DBConnection.commit();
		
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int editProduct(Product product) {
		try {
			PreparedStatement stmt = DBConnection.prepare("UPDATE t_product SET pname=?, price=?, pimage=?, cid=?, pdes=? WHERE pid=?");
			stmt.setString(1, product.getPname());
			stmt.setDouble(2, product.getPrice());
			stmt.setString(3, product.getPimage());
			stmt.setString(4, product.getCid());
			stmt.setString(5, product.getPdesc().getDescription());
			stmt.setString(6, product.getPid());

			int rs=stmt.executeUpdate();
			DBConnection.commit();
		
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delProduct(String pid) {
		try {
			PreparedStatement stmt = DBConnection.prepare("DELETE FROM t_product WHERE pid=?");

			stmt.setString(1, pid);

			int rs=stmt.executeUpdate();
			DBConnection.commit();
		
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
