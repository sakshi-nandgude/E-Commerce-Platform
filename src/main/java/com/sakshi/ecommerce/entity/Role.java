package com.sakshi.ecommerce.entity;

//pulls tools to talk to database ex @Entity, @Id
import jakarta.persistence.*;
// pulls library lombok to write boring and repetitive code like getters, setters, constructors, toString, etc.
import lombok.*;

//tells database this class is table in my database
@Entity
// name the database table or else spring will set classname as table name by
// default
@Table(name = "roles")
// automatically creates methods like getId() , setName() we dont have to type
// them
@Getter
@Setter
// Creates a blank constructor db needs this to create objects
@NoArgsConstructor
// creates a constructor with all the fields as parameters
@AllArgsConstructor
// allows you to create new role object in very clean way
@Builder
public class Role {
    // mark this field as primary key in database
    @Id
    // auto generate the value of id when we create new role object
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // add a column to the database with rile unique and not null
    @Column(unique = true, nullable = false)
    private String name;
}
