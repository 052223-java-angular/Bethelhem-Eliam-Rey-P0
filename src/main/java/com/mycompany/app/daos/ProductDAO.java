package com.mycompany.app.daos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.app.models.Product;
import com.mycompany.app.utils.ConnectionFactory;

public class ProductDAO implements CrudDAO<Product> {

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
  public Product findByname(String name) {
    try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
        String sql = "SELECT name,description ,price FROM products WHERE name = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Set the username parameter for the prepared statement
            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Create a new User object and populate it with data from the result set
                    Product pro = new Product();
                    //pro.setId(rs.getString("id"));
                    pro.setName(rs.getString("name"));
                    pro.setDescription(rs.getString("description"));
                    pro.setPrice(rs.getDouble("price"));
                    return pro;
                }
            }
        }

    } catch (SQLException e) {
        throw new RuntimeException("Unable to connect to the database", e);
    } catch (IOException e) {
        throw new RuntimeException("Cannot find application.properties", e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException("Unable to load JDBC driver", e);
    }

    return null;
}
}
