package com.kk.bookstore.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
	private String isbn;
	private String name;
	private Publisher publisher;
	private List<Chapter> chapters;
	
}
