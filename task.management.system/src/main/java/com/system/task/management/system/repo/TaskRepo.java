package com.system.task.management.system.repo;

import com.system.task.management.system.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface TaskRepo extends JpaRepository<Task, String> {
    @Query(value = "SELECT task_id FROM task WHERE task_id like ?% ORDER BY CAST(SUBSTRING(task_id,?) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastId(String s, int i);

    Task getTaskByTaskIdEquals(String taskId);
}
