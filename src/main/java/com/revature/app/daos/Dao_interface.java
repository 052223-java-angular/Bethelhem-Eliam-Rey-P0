package com.revature.app.daos;

import java.util.List;

public interface Dao_interface<T> {
   
        void save(T obj);
    
        void update(String id);
    
        void delete(String id);
    
        T findById(String id);
    
        List<T> findAll();
    
    
}
