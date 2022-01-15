package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import rmit.edu.JobATMService.models.JobCategory;

import javax.validation.constraints.NotBlank;


@Data
public class JobRequest {
    private Long id;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 50)
    private String jobTitle;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 50)
    private String position;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 250)
    private String companyName;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 50)
    private Long salary;

    @NotBlank(message = "Fill in the input field")
    @Length(max = 250)
    private String description;

    @NotBlank(message = "Fill in the input field")
    private String jobCategory;

    @NotBlank(message = "Fill in the input field")
    private String datePosted;
}
