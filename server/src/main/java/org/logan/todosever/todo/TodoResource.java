package org.logan.todosever.todo;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoResource {

  private TodoService todoService;

  public TodoResource(TodoService todoService) {
    super();
    this.todoService = todoService;
  }

  @GetMapping
  public List<Todo> retrieveAllTodos() {
    return todoService.findAllTodos();
  }
  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    return todoService.createTodo(todo);
  }

  @PutMapping("/{id}")
  public Todo updateTodo(@PathVariable int id, @RequestBody Todo todo){
    return todoService.updateTodo(id, todo);
  }

  @DeleteMapping("/{id}")
  public void deleteTodo(@PathVariable int id){
    todoService.deleteById(id);
  }

  @GetMapping("/user/{username}")
  public List<Todo> retrieveUserTodos(@PathVariable String username) {
    return todoService.findTodosByUsername(username);
  }

  @GetMapping("/{id}")
  public Todo retrieveTodo( @PathVariable int id) {
    return todoService.findTodoByTodoId(id);
  }
}
