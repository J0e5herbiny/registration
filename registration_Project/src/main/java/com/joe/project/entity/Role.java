package com.joe.project.entity;

import com.joe.project.dto.RoleDto;

import javax.persistence.*;

@Entity
@Table
public class Role {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
//            generator = "role_id")
//    @SequenceGenerator(
//            name = "role_id",
//            sequenceName = "role_id",
//            allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Role() {
    }

    public Role(Long id,
                String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public RoleDto roleDto(){
        Role role = new Role();
        return new RoleDto(
                role.getId(),
                role.getName()
        );
    }

}
