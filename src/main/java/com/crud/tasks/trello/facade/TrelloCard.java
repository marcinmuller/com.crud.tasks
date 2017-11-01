package com.crud.tasks.trello.facade;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marcin Muller on 01.11.17.
 */
@Getter
@AllArgsConstructor
public class TrelloCard {
    private String name;
    private String description;
    private String pos;
    private String listId;
}
