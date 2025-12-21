package com.example.product.job.implement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.product.job.Job;
import com.example.product.job.JobService;

@Service 
public class JobServiceImplementation implements JobService {
    
    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll(){
        return jobs;
    }

    @Override
    public void createJob(Job job){
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job findById(Long Id){
        for(Job job: jobs){
            if(job.getId().equals(Id)){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long Id){
        Iterator<Job> it = jobs.iterator();

        while(it.hasNext()){
            Job job = it.next();
            if(job.getId().equals(Id)){
                it.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateJobById(Long Id, Job updatedJob){
        for(Job job : jobs){
            if(job.getId().equals(Id)){
                job.setDescription(updatedJob.getDescription());
                job.setLocation(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setTitle(updatedJob.getTitle());
                return true;
            }
        }
        return false;
    }
}


