package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * Created by Marcin Muller on 09.09.17.
 *
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("v1/")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "tasks")
    public List<TaskDto> getTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "task")
    public TaskDto getTask(@RequestParam("taskId") Long taskId) throws TaskNotFoundException {
        return taskMapper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "task")
    public void deleteTask(@RequestParam("taskId") Long taskId) {
        service.deleteTask(taskId);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        service.saveTask(taskMapper.mapToTask(taskDto));
    }
}