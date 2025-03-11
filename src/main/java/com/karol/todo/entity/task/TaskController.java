package com.karol.todo.entity.task;

import com.karol.todo.entity.task.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> allTasks = taskService.getAllTasks();
        return ResponseEntity.ok(allTasks);
    }

    @PostMapping()
    ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        var task = taskService.addNewTask(taskDto);
        return ResponseEntity.ok().body(task);
    }

    @DeleteMapping("/{taskId}")
    ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}")
    ResponseEntity<TaskDto> updateTask(@RequestBody TaskDto taskDto, @PathVariable Long taskId) {
        var task = taskService.updateTask(taskDto, taskId);
        return ResponseEntity.ok().body(task);
    }


}
