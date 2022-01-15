package rmit.edu.JobATMService.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rmit.edu.JobATMService.models.Job;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long>, CrudRepository<Job, Long> {

    Optional<Job> findById(Long id);

    List<Job> findAllByCompanyName(String jobTitle);

    List<Job> findAllByJobTitle(String jobTitle);

    List<Job> findAllByPosition(String position);

    Page<Job> findAll(Pageable pageable);

    Job save(Job job);

    void deleteById(long id);

}
