package com.eworkgroup.automationdb.results;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
public class JobResultController {

    @Autowired
    private JobResultService jobResultService;

    @RequestMapping(value = "/job-results",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<String> jobResults(@RequestBody JobResultDto jobResultDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Request body is not valid");
        }
        if (jobResultDto.testMethodResults().isEmpty()) {
            return ResponseEntity.badRequest().body("At least on test result is required");
        }
        JobResult jr = jobResultService.saveJobResult(jobResultDto);
        log.info("JobResultDto: {}", jobResultDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{aut}/{env}/{id}")
                .buildAndExpand(jobResultDto.aut(), jobResultDto.env(), jr.getId())
                .toUri();
        return ResponseEntity.created(location).body("success");
    }

    @RequestMapping(value = "/job-results/{aut}/{env}",
            method = RequestMethod.GET,
            produces = "application/json")
    public List<JobResult> jobResults(@PathVariable String aut, @PathVariable String env) {
        log.info("aut: {}, env: {}", aut, env);
        return jobResultService.getJobResults(aut.toUpperCase(), env.toUpperCase());
    }

    @RequestMapping(value = "/job-results/{aut}/{env}/1",
            method = RequestMethod.GET,
            produces = "application/json")
    public JobResult jobResults1(@PathVariable String aut, @PathVariable String env) {
        log.info("aut: {}, env: {}", aut, env);
        return jobResultService.getJobResults1(aut.toUpperCase(), env.toUpperCase());
    }
}
