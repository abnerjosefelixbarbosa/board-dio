package com.org.board_ui.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	private Long id;
	private String title;
	private String description;
	private String date;
	private Column column;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<Block> blocks;
}