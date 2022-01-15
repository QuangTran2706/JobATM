package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import rmit.edu.JobATMService.models.Role;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserRequest {
    private long id;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 30)
    private String username;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 50)
    private String email;

    @NotBlank(message = "Fill in the input field")
    @Length(min = 6, max = 50)
    private String password;

    @NotBlank(message = "Fill in the input field")
    private Set<Role> roles = new HashSet<>();

}
