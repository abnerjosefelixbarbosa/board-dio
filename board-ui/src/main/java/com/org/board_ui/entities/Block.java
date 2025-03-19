package com.org.board_ui.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Block {
	private Long id;
	private String blockDescrition;
	private String blockDate;
	private String unblockDescrition;
	private String unblockDate;
}