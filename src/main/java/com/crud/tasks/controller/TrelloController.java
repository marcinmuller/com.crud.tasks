package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

//    @Autowired
//    private TrelloClient trelloClient;

/*    @Autowired
    private TrelloService trelloService;
*/

    @Autowired
    private TrelloFacade trelloFacade;

//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloClient.getTrelloBoards();
//    }

 /*   @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }
*/
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }
//    public void getTrelloBoards() {
//        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
//
//        trelloBoards
//                .stream()
//                .filter(t->t.getId()!=null)
//                .filter(t->t.getName()!=null)
//                .filter(t->t.getName().contains("Kodilla"))
//                .forEach(t-> {
//                    System.out.println("-------------->"+t.getId() + " " + t.getName());
//                    System.out.println("This board contains lists: ");
//                    t.getLists().forEach(trelloList ->
//                            System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.getIsClosed()));
//                });
//
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
//    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
//        return trelloClient.createNewCard(trelloCardDto);
//    }

/*    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloService.createdTrelloCard(trelloCardDto);
    }
*/
    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloFacade.createCard(trelloCardDto);
    }
}