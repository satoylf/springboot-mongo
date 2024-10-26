package proj.dto;

import java.io.Serializable;

import proj.domain.User;

public class AuthorDTO implements Serializable {

  private String id;
  private String name;

  public AuthorDTO() {
  }

  public AuthorDTO(User obj) {
    id = obj.getId();
    name = obj.getName();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
