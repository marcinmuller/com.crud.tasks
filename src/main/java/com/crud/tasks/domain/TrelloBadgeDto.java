package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Marcin Muller on 15.10.17.
 */
@AllArgsConstructor
@Getter
public class TrelloBadgeDto {
    private int votes;
    private TrelloAttachmentsByTypeDto attachmentsByType;
}
