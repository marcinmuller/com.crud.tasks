package com.crud.tasks;


import com.crud.tasks.controller.TaskController;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Assert;
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
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class Test21 {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DbService dbService;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testGetTasks() throws Exception {
//        given
        List<TaskDto> list = new ArrayList<>();
        list.add(new TaskDto(1L,"title","content"));
        when(taskMapper.mapToTaskDtoList(anyList())).thenReturn(list);
//        when and given
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].title", is("title")))
                .andExpect(jsonPath("$[0].content", is("content")));

    }

    @Test
    public void testGetTask() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L,"title1","content1");
        Task task = new Task(1L,"1","1");
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(dbService.getTaskById(1L)).thenReturn(Optional.ofNullable(task));
//        when and given
        mockMvc.perform(get("/v1/task").param("taskId","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("title1")))
                .andExpect(jsonPath("$.content",is("content1")));
    }

    @Test
    public void testUpdateTask() throws Exception {
        //given
        TaskDto taskDto = new TaskDto(1L,"1","1");
        Task task = new Task(1L,"1","1");
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        when(taskMapper.mapToTaskDto(ArgumentMatchers.any(Task.class))).thenReturn(taskDto);
        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(dbService.saveTask(ArgumentMatchers.any(Task.class))).thenReturn(task);
//        when and given
        mockMvc.perform(put("/v1/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("1")))
                .andExpect(jsonPath("$.content",is("1")));
    }

    @Test
    public void testMapToTaskDto(){
        //given
        TaskMapper mapper=new TaskMapper();
        Task task=new Task(1L,"1","1111");
        TaskDto taskDto=new TaskDto(1L,"1","1111");
        //when & then
        Assert.assertEquals(taskDto.getId(), mapper.mapToTaskDto(task).getId());
        Assert.assertEquals(taskDto.getContent(), mapper.mapToTaskDto(task).getContent());
        Assert.assertEquals(taskDto.getTitle(), mapper.mapToTaskDto(task).getTitle());
    }

    @Test
    public void testMapToTask(){
        //given
        TaskMapper mapper=new TaskMapper();
        Task task=new Task(1L,"1","1111");
        TaskDto taskDto=new TaskDto(1L,"1","1111");
        //when & then
        Assert.assertEquals(task.getId(), mapper.mapToTask(taskDto).getId());
        Assert.assertEquals(task.getContent(), mapper.mapToTask(taskDto).getContent());
        Assert.assertEquals(task.getTitle(), mapper.mapToTask(taskDto).getTitle());
    }

    @Test
    public void testMapList(){
        //given
        TaskMapper mapper=new TaskMapper();
        Task task=new Task(1L,"1","1111");
        TaskDto taskDto=new TaskDto(1L,"1","1111");
        List<Task> taskList=new ArrayList<>();
        List<TaskDto> taskDtos=new ArrayList<>();
        taskDtos.add(taskDto);
        taskList.add(task);
        //when & then
        Assert.assertEquals(taskDtos.get(0).getTitle(),mapper.mapToTaskDtoList(taskList).get(0).getTitle());
        Assert.assertEquals(taskDtos.get(0).getContent(),mapper.mapToTaskDtoList(taskList).get(0).getContent());
        Assert.assertEquals(taskDtos.get(0).getId(),mapper.mapToTaskDtoList(taskList).get(0).getId());
    }
}
