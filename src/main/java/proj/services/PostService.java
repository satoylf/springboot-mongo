package proj.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import proj.domain.Post;
import proj.repository.PostRepository;
import proj.services.exception.ObjectNotFoundException;

@Service
public class PostService{

  @Autowired
  private PostRepository repo;

  public Post findById(String id){
    return repo.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found"));
  }
}
