package com.example.product.job.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.product.job.Job;
import com.example.product.job.JobService;

@Service
public class JobServiceImplementation implements JobService {
    
    List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll(){
        return jobs;
    }

    @Override
    public void createJob(Job job){
        jobs.add(job);
    }
}


