package com.karol.todo.entity.task;

import com.karol.todo.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findAllByUserId(Long id);
}
