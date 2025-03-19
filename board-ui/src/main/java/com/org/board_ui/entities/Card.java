package com.org.board_ui.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	private Long id;
	private String title;
	private String description;
	private String date;
	private Column column;
	private Block block;
}