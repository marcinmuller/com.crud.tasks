package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.domain.TrelloListDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Marcin Muller on 01.11.17.
 */
public class TrelloMapperTest {

    private TrelloMapper trelloMapper=new TrelloMapper();

    @Test
    public void mapToCardDto() throws Exception {
        //given
        TrelloCard card = new TrelloCard("card_name_1","card_description_1","card_pos_1","card_listId_1");
        //when
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        //then
        Assert.assertEquals(cardDto.getName(),"card_name_1");
        Assert.assertEquals(cardDto.getDescription(),"card_description_1");
        Assert.assertEquals(cardDto.getPos(),"card_pos_1");
        Assert.assertEquals(cardDto.getListId(),"card_listId_1");
        Assert.assertEquals(cardDto.getPos(),"card_pos_1");
    }

    @Test
    public void mapToCard() throws Exception {
        //given
        TrelloCardDto cardDto = new TrelloCardDto("card_name_1","card_description_1","card_pos_1","card_listId_1");
        //when
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        //then
        Assert.assertEquals(card.getName(),"card_name_1");
        Assert.assertEquals(card.getDescription(),"card_description_1");
        Assert.assertEquals(card.getPos(),"card_pos_1");
        Assert.assertEquals(card.getListId(),"card_listId_1");
    }

    @Test
    public void mapToList() throws Exception {
        //given
        List<TrelloListDto> listDtos=new ArrayList<>();
        listDtos.add(new TrelloListDto("list_id_1","list_name_1",false));
        listDtos.add(new TrelloListDto("list_id_2","list_name_2",true));
        //when
        List<TrelloList> list = trelloMapper.mapToList(listDtos);
        //then
        Assert.assertEquals(list.get(0).getId(),"list_id_1");
        Assert.assertEquals(list.get(0).getName(), "list_name_1");
        Assert.assertEquals(list.get(0).isClosed(),false);
        Assert.assertEquals(list.get(1).getId(),"list_id_2");
        Assert.assertEquals(list.get(1).getName(), "list_name_2");
        Assert.assertEquals(list.get(1).isClosed(),true);
    }

    @Test
    public void mapToListDto() throws Exception {
        //given
        List<TrelloList> list=new ArrayList<>();
        list.add(new TrelloList("list_id_1","list_name_1",false));
        list.add(new TrelloList("list_id_2","list_name_2",true));
        //when
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(list);
        //then
        Assert.assertEquals(listDtos.get(0).getId(),"list_id_1");
        Assert.assertEquals(listDtos.get(0).getName(), "list_name_1");
        Assert.assertEquals(listDtos.get(0).isClosed(),false);
        Assert.assertEquals(listDtos.get(1).getId(),"list_id_2");
        Assert.assertEquals(listDtos.get(1).getName(), "list_name_2");
        Assert.assertEquals(listDtos.get(1).isClosed(),true);
    }

    @Test
    public void mapToBoards() throws Exception {
        //given
        List<TrelloListDto> listDtos1=new ArrayList<>();
        listDtos1.add(new TrelloListDto("list_id_1","list_name_1",false));
        listDtos1.add(new TrelloListDto("list_id_2","list_name_2",true));
        List<TrelloListDto> listDtos2=new ArrayList<>();
        listDtos2.add(new TrelloListDto("list_id_3","list_name_3",false));

        List<TrelloBoardDto> boardDtos = new ArrayList<>();
        boardDtos.add(new TrelloBoardDto("id_0", "name_0", listDtos1));
        boardDtos.add(new TrelloBoardDto("id_1", "name_1", listDtos2));
        //when
        List<TrelloBoard> boards = trelloMapper.mapToBoards(boardDtos);
        //then
        Assert.assertEquals(boards.get(0).getId(),"id_0");
        Assert.assertEquals(boards.get(1).getId(),"id_1");
        Assert.assertEquals(boards.get(0).getName(),"name_0");
        Assert.assertEquals(boards.get(1).getName(),"name_1");
        Assert.assertEquals(boards.get(0).getLists().get(1).getId(), trelloMapper.mapToList(listDtos1).get(1).getId());
        Assert.assertEquals(boards.get(1).getLists().get(0).getName(), trelloMapper.mapToList(listDtos2).get(0).getName());
    }

    @Test
    public void mapToBoardsDto() throws Exception {
        //given
        List<TrelloList> list1=new ArrayList<>();
        list1.add(new TrelloList("list_id_1","list_name_1",false));
        List<TrelloList> list2=new ArrayList<>();
        list2.add(new TrelloList("list_id_3","list_name_3",false));

        List<TrelloBoard> board = new ArrayList<>();
        board.add(new TrelloBoard("id_0", "name_0", list1));
        board.add(new TrelloBoard("id_1", "name_1", list2));
        //when
        List<TrelloBoardDto> boardDtos = trelloMapper.mapToBoardsDto(board);
        //then
        Assert.assertEquals(boardDtos.get(0).getId(),"id_0");
        Assert.assertEquals(boardDtos.get(1).getId(),"id_1");
        Assert.assertEquals(boardDtos.get(0).getName(),"name_0");
        Assert.assertEquals(boardDtos.get(1).getName(),"name_1");
        Assert.assertEquals(boardDtos.get(0).getLists().get(0).getId(), "list_id_1");
        Assert.assertEquals(boardDtos.get(1).getLists().get(0).getName(), "list_name_3");
        Assert.assertEquals(boardDtos.get(1).getLists().get(0).isClosed(), false);
    }


}