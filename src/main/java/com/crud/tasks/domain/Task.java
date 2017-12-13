package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Marcin Muller on 08.09.17.
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Long id;
    @Column(name="name")
    private String title;
    @Column(name="description")
    private String content;
}