package rmit.edu.JobATMService.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "jobCategory")
@Getter
@Setter
@NoArgsConstructor
public class JobCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EJobCategory jobCategory;

    public JobCategory(EJobCategory jobCategory) {
        this.jobCategory = jobCategory;
    }
}
