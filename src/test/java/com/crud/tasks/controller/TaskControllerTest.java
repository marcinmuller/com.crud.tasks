package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TaskController taskController;

//    @Test
//    public void testGetTasks() throws Exception {
//        given
//        List<TaskDto> list = new ArrayList<>();
//        list.add(new TaskDto(1L,"title","content"));
//        when(taskController.getTasks()).thenReturn(list);
//        when and given
//        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$",hasSize(1)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].title", is("title")))
//                .andExpect(jsonPath("$[0].content", is("content")));
//
//    }

    @Test
    public void testGetTask() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L,"title1","content1");
        when(taskController.getTask(1L)).thenReturn(taskDto);
        //when and given
        mockMvc.perform(get("/v1/task/getTask").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("title1")))
                .andExpect(jsonPath("$.content",is("content1")));
    }

    @Test
    public void testDeleteTask() throws Exception {
        mockMvc.perform(delete("/v1/task/deleteTask").param("taskId","5").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L,"1","1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskController.updateTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(taskDto);
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("1")))
                .andExpect(jsonPath("$.content",is("1")));
    }

    @Test
    public void testCreateTask() throws Exception {
        TaskDto taskDto = new TaskDto(1L,"1","1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}