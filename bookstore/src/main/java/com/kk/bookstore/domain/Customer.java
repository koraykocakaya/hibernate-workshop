package com.kk.bookstore.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@Column(name="ID")	
	private Long id;
	
	private String name;
	
	@OneToOne (cascade = CascadeType.PERSIST)
	@JoinColumn(name = "passport_id")
	@MapsId
	private Passport passport;
}
