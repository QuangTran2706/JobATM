package rmit.edu.JobATMService.service.Impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import rmit.edu.JobATMService.models.Job;
import rmit.edu.JobATMService.models.User;
import rmit.edu.JobATMService.payload.request.JobRequest;
import rmit.edu.JobATMService.respository.JobRepository;
import rmit.edu.JobATMService.service.JobService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final ModelMapper modelMapper;
    private final JobRepository jobRepository;

    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getAllJob(Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public void delete(long id) {
        Job job = jobRepository.getById(id);
        jobRepository.delete(job);
    }

    @Override
    public Job updateJob(JobRequest jobRequest) {
        Job newJob = modelMapper.map(jobRequest, Job.class);
        jobRepository.save(newJob);
        return newJob;
    }

    @Override
    public List<Job> findJobBetween(String start, String end) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdformat.parse(start);
        Date endDate = sdformat.parse(end);

        List<Job> allJob = jobRepository.findAll();
        List<Job> filteredJob = new ArrayList<>();
        for (Job job:allJob) {
            Date datePosted = sdformat.parse(job.getDatePosted());
            if(datePosted.after(startDate) & datePosted.before(endDate)) {
                filteredJob.add(job);
            }
        }
        return filteredJob;
    }

    @Override
    public List<Job> findJobByDay(String day) throws ParseException {
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
        Date filteredDay = sdformat.parse(day);
        List<Job> allJob = jobRepository.findAll();
        List<Job> filteredJob = new ArrayList<>();
        for (Job job:allJob) {
            Date datePosted = sdformat.parse(job.getDatePosted());
            if(datePosted.equals(filteredDay)) {
                filteredJob.add(job);
            }
        }

        return filteredJob;
    }
}
