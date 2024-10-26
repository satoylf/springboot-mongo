package proj.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import proj.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  
  List<Post> findByTitleContainingIgnoreCase(String text); // This method is automatically implemented by Spring Data
  
  // ?0 = first parameter, i = case insensitive
  @Query("{ 'title': { $regex: ?0, $options: 'i' } }") // Custom Query
  List<Post> searchTitle(String text);

  // ?1 = second parameter, ?2 = third parameter
  // $gte = greater than or equal, $lte = less than or equal
  @Query("{ $and: [ { date: {$gte: ?1} }, {date: {$lte: ?2} }, {$or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
  List<Post> completeSearch(String text, Date minDate, Date maxDate);
}
