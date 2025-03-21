package com.org.board_ui.persistences.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Column {
	private Long id;
	private String name;
	private ColumnType type;
	private Integer order;
	private Board board;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Card> cards;
}