package com.org.board_ui.persistences.daos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mysql.cj.jdbc.StatementImpl;
import com.org.board_ui.persistences.entities.Column;
import com.org.board_ui.persistences.entities.ColumnType;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ColumnDAO {
	private final Connection connection;

	public Column insert(final Column column) throws Exception {
        var sql = "INSERT INTO column_tb (name, column_type, column_order, board_id) VALUES (?, ?, ?, ?);";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setString(1, column.getName());
            statement.setString(2, column.getType().name());
            statement.setInt(3, column.getOrder());
            statement.setLong(4, column.getBoard().getId());
            statement.executeUpdate();
            if (statement instanceof StatementImpl impl){
            	column.setId(impl.getLastInsertID());
            }
            return column;
        } catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			connection.close();
		}
        
        return column;
    }

    public List<Column> listByBoardId(final Long id) throws SQLException{
        List<Column> columns = new ArrayList<>();
        var sql = "SELECT id, name, column_order, column_type FROM column_tb WHERE board_id = ? ORDER BY column_order";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()){
                var column = new Column();
                column.setId(resultSet.getLong("id"));
                column.setName(resultSet.getString("name"));
                column.setOrder(resultSet.getInt("column_order"));
                column.setType(ColumnType.valueOf(resultSet.getString("column_type")));
                
                columns.add(column);
            }
        } catch (Exception e) {
        	System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
        
        return columns;
    }

    public List<Column> findByBoardIdWithDetails(final Long boardId) throws SQLException {
        List<Column> dtos = new ArrayList<>();
        var sql = "SELECT bc.id, bc.name, bc.kind, "
        		+ "(SELECT COUNT(c.id) FROM CARDS c WHERE c.board_column_id = bc.id) cards_amount"
        		+ "FROM BOARDS_COLUMNS bc"
        		+ "WHERE board_id = ?"
        		+ "ORDER BY `order`;";
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, boardId);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            while (resultSet.next()){
                var dto = new BoardColumnDTO(
                        resultSet.getLong("bc.id"),
                        resultSet.getString("bc.name"),
                        findByName(resultSet.getString("bc.kind")),
                        resultSet.getInt("cards_amount")
                );
                dtos.add(dto);
            }
            return dtos;
        }
    }

    public Optional<BoardColumnEntity> findById(final Long boardId) throws SQLException{
        var sql =
        """
        SELECT bc.name,
               bc.kind,
               c.id,
               c.title,
               c.description
          FROM BOARDS_COLUMNS bc
          LEFT JOIN CARDS c
            ON c.board_column_id = bc.id
         WHERE bc.id = ?;
        """;
        try(var statement = connection.prepareStatement(sql)){
            statement.setLong(1, boardId);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                var entity = new BoardColumnEntity();
                entity.setName(resultSet.getString("bc.name"));
                entity.setKind(findByName(resultSet.getString("bc.kind")));
                do {
                    var card = new CardEntity();
                    if (isNull(resultSet.getString("c.title"))){
                        break;
                    }
                    card.setId(resultSet.getLong("c.id"));
                    card.setTitle(resultSet.getString("c.title"));
                    card.setDescription(resultSet.getString("c.description"));
                    entity.getCards().add(card);
                }while (resultSet.next());
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }

}