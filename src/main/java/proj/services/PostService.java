package proj.services;

import java.util.Date;
import java.util.List;

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

  public List<Post> findByTitle(String text){
    //return repo.findByTitleContainingIgnoreCase(text);
    return repo.searchTitle(text);
  }

  public List<Post> completeSearch(String text, Date minDate, Date maxDate){
    maxDate = new Date(maxDate.getTime() + 86400000); // Add one day to the maxDate (milliseconds)
    return repo.completeSearch(text, minDate, maxDate);
  }
}
