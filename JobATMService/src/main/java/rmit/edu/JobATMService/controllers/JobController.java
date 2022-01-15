package rmit.edu.JobATMService.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmit.edu.JobATMService.helper.DateValidatorCustom;
import rmit.edu.JobATMService.models.Job;
import rmit.edu.JobATMService.payload.request.JobRequest;
import rmit.edu.JobATMService.service.Impl.JobServiceImpl;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
public class JobController {

    private final JobServiceImpl jobService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Job>> getAllJob() {
        return ResponseEntity.ok(jobService.getAllJob());
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        //http://localhost:8080/job?pageSize=5&pageNo=1
        List<Job> list = jobService.getAllJob(pageNo, pageSize);

        return ResponseEntity.ok(jobService.getAllJob(pageNo, pageSize));
    }

    @PostMapping("/update")
    public ResponseEntity<Job> updateJob(@RequestBody JobRequest jobRequest) {
        return ResponseEntity.ok(jobService.updateJob(jobRequest));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteJob(@PathVariable(value = "id") Long id) {
        jobService.delete(id);
    }

    @GetMapping("/getBetween")
    public ResponseEntity<List<Job>> getJobBetween(
            @RequestParam(defaultValue = "2000-1-1") String startDate,
            @RequestParam(defaultValue = "2000-1-1") String endDate) throws ParseException {
        DateValidatorCustom dateValidatorCustom = new DateValidatorCustom("yyyy-MM-dd");
        if(!dateValidatorCustom.isValid(startDate) |
                !dateValidatorCustom.isValid(endDate)) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Job> filterJob = jobService.findJobBetween(startDate, endDate);
        return ResponseEntity.ok(filterJob);
    }

    @GetMapping("/getByDay")
    public ResponseEntity<List<Job>> getByDate(
            @RequestParam(defaultValue = "2000-1-1") String date) throws ParseException {
        DateValidatorCustom dateValidatorCustom = new DateValidatorCustom("yyyy-MM-dd");
        if(!dateValidatorCustom.isValid(date)) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        List<Job> filterJob = jobService.findJobByDay(date);
        return ResponseEntity.ok(filterJob);
    }

}
