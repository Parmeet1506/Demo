package com.demo.controller;

import com.demo.model.Demo;
import com.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * PUT API - Insert or Update data in DB
     * Endpoint: PUT /api/demo
     * Example: {"id": 1, "name": "John Doe"}
     */
    @PutMapping
    public ResponseEntity<Demo> insertOrUpdate(@RequestBody Demo demo) {
        try {
            if (demo.getId() == null) {
                return ResponseEntity.badRequest().build();
            }
            Demo savedDemo = demoService.save(demo);
            return ResponseEntity.ok(savedDemo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * GET API - Retrieve details from DB
     * Endpoint: GET /api/demo/{id}
     * Example: GET /api/demo/1
     */
    @GetMapping("/{id}")
    public ResponseEntity<Demo> getDetails(@PathVariable Long id) {
        try {
            Optional<Demo> demo = demoService.findById(id);
            if (demo.isPresent()) {
                return ResponseEntity.ok(demo.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * DELETE API - Delete a record from DB
     * Endpoint: DELETE /api/demo/{id}
     * Example: DELETE /api/demo/1
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id) {
        try {
            if (demoService.existsById(id)) {
                demoService.deleteById(id);
                return ResponseEntity.ok("Record with ID " + id + " deleted successfully.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting record: " + e.getMessage());
        }
    }
}