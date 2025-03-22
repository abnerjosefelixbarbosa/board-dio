package com.org.board_ui.persistences.daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.mysql.cj.jdbc.StatementImpl;
import com.org.board_ui.persistences.entities.Board;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardDAO {
	private final Connection connection;
	
	public Board insert(final Board board) throws Exception {
		var sql = "INSERT INTO board_tb (name) values (?);";
		
		try (var statement = connection.prepareStatement(sql)) {
			statement.setString(1, board.getName());
			statement.executeUpdate();
			if (statement instanceof StatementImpl impl){
				board.setId(impl.getLastInsertID());
            }
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
		
		return board;
	}
	
	public void delete(final Long id) throws Exception {
        var sql = "DELETE FROM board_tb WHERE id = ?;";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
        	System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
    }

    public Optional<Board> findById(final Long id) throws Exception {
        var sql = "SELECT id, name FROM board_tb WHERE id = ?;";
        var board = new Board();
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            
            var resultSet = statement.getResultSet();
            
            if (resultSet.next()){
                board.setId(resultSet.getLong("id"));
                board.setName(resultSet.getString("name"));
            }
            
            return Optional.of(board);
        } catch (Exception e) {
        	System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
        
        return Optional.empty();
    }

    public boolean exists(final Long id) throws SQLException {
        var sql = "SELECT 1 FROM board_tb WHERE id = ?;";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            
            return statement.getResultSet().next();
        } catch (Exception e) {
        	System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
        
        return false;
    }
}
