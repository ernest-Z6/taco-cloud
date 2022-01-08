package tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tacos.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {

}
