package com.crud.tasks.trello.validator;

import com.crud.tasks.trello.facade.TrelloBoard;
import com.crud.tasks.trello.facade.TrelloCard;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Marcin Muller on 01.11.17.
 */
@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloFacade.class);

    public void validateCard(final TrelloCard trelloCard){
        if(trelloCard.getName().contains("test")){
            LOGGER.info("Someone is testing my application");
        }else{
            LOGGER.info("Seems that my application is used in proper way");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards){
        LOGGER.info("starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard.getName().equalsIgnoreCase("Test"))
                .collect(Collectors.toList());
        LOGGER.info("board has been filtered. current list size: "+filteredBoards.size());
        return filteredBoards;
    }
}
