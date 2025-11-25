package com.example.product.job;

import java.util.List;

public interface JobService {

    List<Job> findAll();
    void createJob(Job job);
}


