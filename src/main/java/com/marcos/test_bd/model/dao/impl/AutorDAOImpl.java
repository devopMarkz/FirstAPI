package com.marcos.test_bd.model.dao.impl;

import com.marcos.test_bd.exception.DbException;
import com.marcos.test_bd.model.dao.AutorDAO;
import com.marcos.test_bd.model.entities.Autor;
import com.marcos.test_bd.util.db.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    private Connection connection;

    public AutorDAOImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public void update(Long id, Autor autor) {
        String sql = "UPDATE autor SET Name = ?, Email = ? WHERE Id = ?";
        try (PreparedStatement updateStmt = connection.prepareStatement(sql)){
            updateStmt.setString(1, autor.getName());
            updateStmt.setString(2, autor.getEmail());
            updateStmt.setLong(3, id);
            updateStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM autor WHERE Id = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(sql)){
            deleteStmt.setLong(1, id);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void insert(Autor autor) {
        String sql = "INSERT INTO autor (Name, Email) VALUES (?, ?)";
        try (PreparedStatement insertStmt = connection.prepareStatement(sql)){
            insertStmt.setString(1, autor.getName());
            insertStmt.setString(2, autor.getEmail());
            insertStmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public Autor findById(Long id) {
        String sql = "SELECT * FROM autor WHERE Id = ?";
        ResultSet rs = null;
        try (PreparedStatement selectStmt = connection.prepareStatement(sql)){
            selectStmt.setLong(1, id);
            rs = selectStmt.executeQuery();

            if(rs.next()) {
                return instanciaAutor(rs);
            } else throw new DbException("Autor não encontrado.");


        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Autor> findAll() {
        String sql = "SELECT * FROM autor";
        ResultSet rs = null;
        List<Autor> autorList = new ArrayList<>();
        try (PreparedStatement selectStmt = connection.prepareStatement(sql)){
            rs = selectStmt.executeQuery();

            while (rs.next()) {
                autorList.add(instanciaAutor(rs));
            }

            return autorList;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
        }
    }

    private Autor instanciaAutor(ResultSet rs) throws SQLException{
        return new Autor(rs.getLong("Id"), rs.getString("Name"), rs.getString("Email"));
    }
}
