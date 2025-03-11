package com.karol.todo.entity.task;

import com.karol.todo.entity.task.dto.TaskDto;
import com.karol.todo.entity.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepo taskRepo;
    private final UserService userService;

    public List<TaskDto> getAllTasks() {
        Long userId = userService.getLoggedInUserId();
        List<Task> tasks = taskRepo.findAllByUserId(userId);
        return tasks.stream().map(task -> new TaskDto(
                task.getId(),
                task.getTaskName(),
                task.getDeadline()))
                .toList();
    }

    public TaskDto addNewTask(TaskDto taskDto) {
        var userId  = userService.getLoggedInUserId();
        var user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        var task = Task.builder()
                .taskName(taskDto.getTaskName())
                .deadline(taskDto.getDeadline())
                .user(user)
                .build();
        var taskSaved = taskRepo.save(task);
        return TaskDto.builder()
                .id(taskSaved.getId())
                .taskName(taskSaved.getTaskName())
                .deadline(taskSaved.getDeadline())
                .build();
    }

    public void deleteTask(Long taskId) {
        taskRepo.deleteById(taskId);
    }

    public TaskDto updateTask(TaskDto taskDto, Long taskId) {
        var task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTaskName(taskDto.getTaskName());
        task.setDeadline(taskDto.getDeadline());
        var taskUpdated = taskRepo.save(task);
        return TaskDto.builder()
                .id(taskUpdated.getId())
                .taskName(taskUpdated.getTaskName())
                .deadline(taskUpdated.getDeadline())
                .build();
    }
}
