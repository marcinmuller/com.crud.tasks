package com.crud.tasks.domain;

import lombok.Getter;
import lombok.AllArgsConstructor;
/**
 * Created by Marcin Muller on 08.09.17.
 */
@Getter
@AllArgsConstructor
public class TaskDto {
    private String id;
    private String title;
    private String content;
}
