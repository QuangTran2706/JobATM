package rmit.edu.JobATMService.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmit.edu.JobATMService.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findById(long id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Page<User> findAll(Pageable pageable);

    List<User> findAll();

    void deleteById(long id);
}

