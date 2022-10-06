package com.kk.bookstore.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.kk.bookstore.enums.MovieGenre;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer year;
	
	private String name;
	
	@Singular
	@ManyToMany (cascade = CascadeType.PERSIST)
	@JoinTable(
			name = "movie_actor",
			joinColumns = {@JoinColumn(name = "actor_id")},
			inverseJoinColumns = {@JoinColumn(name = "movie_id")}
			)
	private List<Actor> actors;
	
	@Enumerated (EnumType.STRING)
	private MovieGenre genre;
}
