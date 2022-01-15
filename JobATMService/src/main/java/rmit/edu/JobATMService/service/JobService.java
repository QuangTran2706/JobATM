package rmit.edu.JobATMService.service;

import rmit.edu.JobATMService.models.Job;
import rmit.edu.JobATMService.payload.request.JobRequest;

import java.text.ParseException;
import java.util.List;

public interface JobService {

    List<Job> getAllJob();

    List<Job> getAllJob(Integer pageNo, Integer pageSize);

    void delete(long id);

    Job updateJob(JobRequest jobRequest);

    List<Job> findJobBetween(String start, String end) throws ParseException;

    List<Job> findJobByDay(String day) throws ParseException;
}
