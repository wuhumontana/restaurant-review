package com.ex.base.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Registrar {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long courseNumber;
	private int studentId;
	
	public String toString() {
		return courseNumber + ": " + studentId;
	}
}