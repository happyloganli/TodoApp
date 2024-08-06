package org.logan.todosever.todo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
  private TodoRepository todoRepository;

  /**
   * Constructor for TodoService
   * @param todoRepository Todos repository, autowired
   */
  public TodoService(TodoRepository todoRepository) {
    super();
    this.todoRepository = todoRepository;
  }

  /**
   * Find All To-dos
   * @return return a list of todos of the user
   */
  public List<Todo> findAllTodos(){
    return todoRepository.findAll();
  }

  /**
   * Add a to-do to repository, and set its status to processing
   * @param todo the to-do item to be added
   */
  public Todo createTodo(Todo todo){
    todo.setStatusProcessing();
    todo.setCreateDate(LocalDate.now());
    todo.setId(Todo.getNextTodoId());
    return todoRepository.save(todo);
  }

  /**
   * Delete a to-do item by its id
   * @param todoId the to-do's id
   */
  public void deleteById(int todoId){
    todoRepository.deleteById(todoId);
  }

  /**
   * Update a to-do's information
   * @param todo the to-do item with new information
   */
  public Todo updateTodo(int id, Todo todo){
    Optional<Todo> originalTodo = todoRepository.findById(id);
    if (originalTodo.isPresent()){
      LocalDate createDate = originalTodo.get().getCreateDate();
      todo.setCreateDate(createDate);
    } else {
      todo.setCreateDate(LocalDate.now());
    }
    todo.setId(id);
    return todoRepository.save(todo);
  }

  /**
   * Find To-dos by username, return a list of todos of the user
   * @param username the user's username
   * @return return a list of todos of the user
   */
  public List<Todo> findTodosByUsername(String username){
    return todoRepository.findByUsername(username);
  }

  /**
   * Find to-do by a to-do's id, return a to-do instance
   * @param todoId the to-do's id
   * @return return a to-do instance
   */
  public Todo findTodoByTodoId(int todoId){
    return todoRepository.findById(todoId).get();
  }

}
