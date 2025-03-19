package com.org.board_ui.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
	private Long id;
	private String name;
	private ColumnType type;
	private Integer order;
	private Board board;
}