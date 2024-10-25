package proj.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import proj.domain.User;
import proj.dto.UserDTO;
import proj.services.UserService;

@RestController
@RequestMapping("/users")
public class UserResource { 

  @Autowired
  private UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll(){
    List<User> list = service.findAll();
    List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
    return ResponseEntity.ok().body(listDto);
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id){
    User obj = service.findById(id);
    return ResponseEntity.ok().body(new UserDTO(obj));
  }

  @PostMapping
  public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){
    User obj = service.fromDto(objDto);
    obj = service.insert(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // returns the URI of the new resource created
    return ResponseEntity.created(uri).build(); // returns 201 status code with a header containing the URI of the new resource created
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id){
    service.delete(id);
    return ResponseEntity.noContent().build(); // returns 204 status code with no content
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){
    User obj = service.fromDto(objDto);
    obj.setId(id); // ensures that the object has the correct id
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
  }
}
