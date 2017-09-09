package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marcin Muller on 08.09.17.
 */
@Getter
@AllArgsConstructor
public class Task {
    private String id;
    private String title;
    private String content;
}
