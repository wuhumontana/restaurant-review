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
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long    id;
    private String firstName;
    private String lastName;
    private String address;

   public Student (Long id, String firstName, String lastName, String address) {
       this.id = id;
       this.firstName = firstName;
       this.lastName = lastName;
       this.address = address;
    }

   public String toString() {
	   return "id=" + this.id + ": " + this.firstName + " " + this.lastName + " " + this.address;
   }
}

