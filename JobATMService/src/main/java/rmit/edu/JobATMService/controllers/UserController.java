package rmit.edu.JobATMService.controllers;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rmit.edu.JobATMService.models.User;
import rmit.edu.JobATMService.payload.request.UserRequest;
import rmit.edu.JobATMService.service.Impl.UserServiceImpl;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUser() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @PostMapping("/update")
    public ResponseEntity<User> updateUser (@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.save(userRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<User>> deleteUser(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PostMapping("/applyjob/{user_id}/{job_id}")
    public ResponseEntity<User> addJobs(@PathVariable(value = "user_id") Long userId,
                                        @PathVariable(value = "job_id") Long jobId) {
        return ResponseEntity.ok(userService.applyJob(userId, jobId));
    }

}
