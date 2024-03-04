package exercise.controller;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.TaskMapper;
import exercise.mapper.UserMapper;
import exercise.model.Task;
import exercise.model.User;
import exercise.repository.TaskRepository;
import exercise.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TasksController {
    // BEGIN
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private TaskMapper taskMapper;
    private UserMapper userMapper;

    @GetMapping(path = "")
    public List<TaskDTO> index() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(t -> taskMapper.map(t))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public TaskDTO show(@PathVariable long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id %d not found".formatted(id)));
        return taskMapper.map(task);
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskData) {
        Task task = taskMapper.map(taskData);
        Long idDeveloper = task.getDeveloper().getId();
        User user = userRepository.findById(idDeveloper)
                .orElseThrow(() -> new ResourceNotFoundException("Not found developer wit id %d".formatted(idDeveloper)));
        task.setDeveloper(user);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "/{id}")
    public TaskDTO update(@PathVariable long id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id %d not found".formatted(id)));
        taskMapper.update(taskUpdateDTO, task);
        Long idDeveloper = task.getDeveloper().getId();
        User user = userRepository.findById(idDeveloper)
                .orElseThrow(() -> new ResourceNotFoundException("Not found developer wit id %d".formatted(idDeveloper)));
        task.setDeveloper(user);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
    // END
}
