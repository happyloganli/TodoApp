package org.logan.todosever.todo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class Todo {

  @Id
  private int id;

  private String username;

  private LocalDate createDate;

  @Size(min = 10, message = "Enter at least 10 characters")
  private String title;

  private TodoStatus status;

  private LocalDate targetDate;

  public Todo(LocalDate createDate, String title, TodoStatus status, String username,
      LocalDate targetDate) {
    this.createDate = createDate;
    this.title = title;
    this.id = TodoIdGenerator.getNextId();
    this.status = status;
    this.username = username;
    this.targetDate = targetDate;
  }

  public Todo() {

  }

  public LocalDate getCreateDate() {
    return createDate;
  }

  public String getTitle() {
    return title;
  }

  public int getId() {
    return id;
  }

  public TodoStatus getStatus() {
    return status;
  }

  public String getUsername() {
    return username;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getTargetDate() {
    return targetDate;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCreateDate(LocalDate createDate) {
    this.createDate = createDate;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setStatus(TodoStatus status) {
    this.status = status;
  }

  public void setTargetDate(LocalDate targetDate) {
    this.targetDate = targetDate;
  }

  public static int getNextTodoId() {
    return TodoIdGenerator.getNextId();
  }

  private class TodoIdGenerator {

    private static int lastId = 10;

    public static synchronized int getNextId() {
      return lastId++;
    }
  }

  public void setStatusFinished() {
    this.status = TodoStatus.FINISHED;
  }

  public void setStatusProcessing() {
    this.status = TodoStatus.PROCESSING;
  }

  public void setStatusExpired() {
    this.status = TodoStatus.EXPIRED;
  }

  public void setStatusDeleted() {
    this.status = TodoStatus.DELETED;
  }

  private enum TodoStatus {
    FINISHED, PROCESSING, EXPIRED, DELETED
  }
}

