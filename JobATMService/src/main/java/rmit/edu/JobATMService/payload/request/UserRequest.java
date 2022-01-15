package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import rmit.edu.JobATMService.models.Role;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {
    private long id;

    @Length(max = 30)
    private String username;

    @Length(max = 50)
    private String email;

    @Length(min = 6, max = 50)
    private String password;

    private Set<Role> roles = new HashSet<>();

}
