package proj.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import proj.domain.User;
import proj.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource { 

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<User>> findAll(){
    List<User> list = service.findAll();
    return ResponseEntity.ok().body(list);
  }
}
