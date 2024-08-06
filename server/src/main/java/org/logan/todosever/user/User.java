package org.logan.todosever.user;

public class User {
  private String firstName;
  private String lastName;
  private final int id;
  private String email;

  public User(String firstName, String lastName, int id, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
