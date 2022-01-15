package rmit.edu.JobATMService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(	name = "job")
@Getter
@Setter
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", initialValue = 1, allocationSize = 1)
    private Long id;

    private String jobTitle;

    private String position;

    private String companyName;

    private Long salary;

    private String description;

    private String datePosted;

    private String jobCategory;

    public Job(String jobTitle, String position, String companyName, long salary, String description, String jobCategory, String datePosted) {
        this.jobTitle = jobTitle;
        this.position = position;
        this.companyName = companyName;
        this.salary = salary;
        this.description = description;
        this.jobCategory = jobCategory;
        this.datePosted = datePosted;
    }
}
