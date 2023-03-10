package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
// the purpose of the Repository is to turns Java objects to a data and vice-versa
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    //JPQL is Java Persistence Query Language defined
    //in JPA specification. It is used to create queries
    //against entities to store in a relational database.
    @Query("SELECT p from Post p WHERE " +
            " p.title LIKE CONCAT('%', :query, '%') OR " +
            " p.shortDescription LIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "select * from posts p where p.created_by =:userId", nativeQuery = true)
    List<Post> findPostsByUser(Long userId);

}