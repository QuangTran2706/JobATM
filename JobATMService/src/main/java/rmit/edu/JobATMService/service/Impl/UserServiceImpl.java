package rmit.edu.JobATMService.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rmit.edu.JobATMService.models.Job;
import rmit.edu.JobATMService.models.User;
import rmit.edu.JobATMService.payload.request.UserRequest;
import rmit.edu.JobATMService.respository.JobRepository;
import rmit.edu.JobATMService.respository.UserRepository;
import rmit.edu.JobATMService.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByName(String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public User save(UserRequest user) {
        User newUser = modelMapper.map(user, User.class);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public List<User> deleteUser(long id) {
        userRepository.delete(userRepository.findById(id));
        return userRepository.findAll();
    }

    @Override
    public User applyJob(long userId, long jobId) {
        User user = userRepository.findById(userId);
        Job job = jobRepository.getById(jobId);
        user.addNewJob(job);
        userRepository.save(user);
        return user;
    }
}
