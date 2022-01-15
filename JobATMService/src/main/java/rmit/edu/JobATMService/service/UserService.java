package rmit.edu.JobATMService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rmit.edu.JobATMService.models.User;
import rmit.edu.JobATMService.payload.request.UserRequest;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findUserById(long id);

    Optional<User> findUserByName(String name);

    User save(UserRequest user);

    List<User> deleteUser(long id);

    User applyJob(long userId, long jobId);

}
