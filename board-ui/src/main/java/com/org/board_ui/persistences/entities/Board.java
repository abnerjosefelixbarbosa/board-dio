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
public class Board {
	private Long id;
	private String name;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Column> columns;
}