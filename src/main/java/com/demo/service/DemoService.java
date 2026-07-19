package com.demo.service;

import com.demo.model.Demo;
import com.demo.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DemoService {

    @Autowired
    private DemoRepository demoRepository;

    /**
     * Insert or update a record in the DEMO table
     */
    public Demo save(Demo demo) {
        return demoRepository.save(demo);
    }

    /**
     * Get a record by ID
     */
    public Optional<Demo> findById(Long id) {
        return demoRepository.findById(id);
    }

    /**
     * Delete a record by ID
     */
    public void deleteById(Long id) {
        demoRepository.deleteById(id);
    }

    /**
     * Check if record exists
     */
    public boolean existsById(Long id) {
        return demoRepository.existsById(id);
    }
}