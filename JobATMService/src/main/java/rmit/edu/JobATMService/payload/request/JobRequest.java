package rmit.edu.JobATMService.payload.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import rmit.edu.JobATMService.models.JobCategory;



@Data
public class JobRequest {
    private Long id;

    @Length(max = 50)
    private String jobTitle;

    @Length(max = 50)
    private String position;

    @Length(max = 250)
    private String companyName;

    @Length(max = 50)
    private Long salary;

    @Length(max = 250)
    private String description;

    private String jobCategory;

    private String datePosted;
}
