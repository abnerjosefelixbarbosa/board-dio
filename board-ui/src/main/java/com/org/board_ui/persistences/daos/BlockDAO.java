package com.org.board_ui.persistences.daos;

import java.sql.Connection;
import java.time.LocalDateTime;

import com.org.board_ui.persistences.entities.Block;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BlockDAO {
	private final Connection connection;

    public void block(final Block block) throws Exception {
        var sql = "INSERT INTO block_tb (block_description, block_date, unblock_description, unblock_date, card_id) VALUES (?, ?, ?, ?, ?);";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setString(1, block.getBlockDescrition());
            statement.setString(2, LocalDateTime.now().toString());
            statement.setString(3, null);
            statement.setString(4, null);
            statement.setLong(5, block.getCard().getId());
            statement.executeUpdate();
        } catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
    }

    public void unblock(final Block block) throws Exception{
        var sql = "UPDATE block_tb SET unblock_date = ?, unblock_description = ? WHERE card_id = ? AND unblock_description IS NULL;";
        
        try(var statement = connection.prepareStatement(sql)){
            statement.setString(1, LocalDateTime.now().toString());
            statement.setString(2, block.getUnblockDescrition());
            statement.setLong(3, block.getCard().getId());
            statement.executeUpdate();
        } catch (Exception e) {
        	System.err.println(e.getMessage());
		} finally {
			connection.close();
		}
    }
}