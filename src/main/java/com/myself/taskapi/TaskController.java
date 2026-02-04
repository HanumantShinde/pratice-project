package com.myself.taskapi;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;

    // Constructor - add some sample data
    public TaskController() {
        tasks.add(new Task(nextId++, "Learn Spring Boot", false));
        tasks.add(new Task(nextId++, "Build REST API", false));
    }

    // GET all tasks
    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    // POST create new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(nextId++);
        tasks.add(task);
        return task;
    }

    // PUT endpoint to update a task
    @PutMapping("/{id}")
    public Task ediTask(@PathVariable long id, @RequestBody Task updatedtask){
        for( Task task: tasks){
            if( task.getId().equals(id)){
                task.setTitle(updatedtask.getTitle());
                task.setCompleted(updatedtask.isCompleted());

                return task;
            }
        }
        return null;
    }

    // DELETE endpoint to remove a task
    @DeleteMapping("/{id}")
    public String deletetask(@PathVariable long id){
        for( int i=0;i<tasks.size();i++){
            if( tasks.get(i).getId().equals(id)){
                tasks.remove(i);
                return "deleted succesfully";
            }
        }
        return "error occur";
    } 


}
