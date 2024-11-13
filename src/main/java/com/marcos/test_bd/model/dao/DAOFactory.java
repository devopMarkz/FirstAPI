package com.marcos.test_bd.model.dao;

import com.marcos.test_bd.model.dao.impl.AutorDAOImpl;
import com.marcos.test_bd.util.db.DB;

public class DAOFactory {

    public static AutorDAO createAutorDAO() {
        return new AutorDAOImpl(DB.getConnection());
    }

}
