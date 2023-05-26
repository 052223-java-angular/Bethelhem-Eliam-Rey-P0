package com.revature.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.app.models.Product;
import com.revature.app.utils.ConnectionFactory;

public class ProductDAO implements Dao_interface<Product> {

  @Override
  public void save(Product product) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public void update(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public Product findById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  @Override
  public List<Product> findAll() {
    List<Product> allProducts = new ArrayList<Product>();
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
      String sql = "Select * from products";
      try (PreparedStatement ps = conn.prepareStatement(sql)) {
        try (ResultSet rs = ps.executeQuery()) {
          while (rs.next()) {
            Product prod = new Product();
            prod.setName(rs.getString("name"));
            prod.setDescription(rs.getString("description"));
            prod.setPrice(rs.getDouble("price"));
            allProducts.add(prod);
          }
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Cannot connect to DB");
    } catch (IOException e) {
      throw new RuntimeException("Cannot find application.properties");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException("Unable to load jdbc");
    }
    return allProducts;
  }
}