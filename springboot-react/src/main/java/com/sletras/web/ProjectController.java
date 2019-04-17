package com.sletras.web;

import com.sletras.domain.Project;
import com.sletras.services.MapValidationErrorService;
import com.sletras.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * Created by sergioletras on 07/04/19.
 */
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    ProjectService projectService;
    MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProjectController(ProjectService projectService, MapValidationErrorService mapValidationErrorService) {
        this.projectService = projectService;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")
    public ResponseEntity<?> createOrUpdate(@RequestBody @Valid Project project, BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null)
            return errorMap;

        projectService.saveOrUpdateProject(project);
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
    }

    @GetMapping("/{projectIdentifier}")
    public ResponseEntity<?> findProjectByIdentifier(@PathVariable String projectIdentifier){

        return new ResponseEntity<Project>(projectService.findProjectByIdentifier(projectIdentifier), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Project> findAllProjects(){
        return projectService.findAllProjects();
    }

    @DeleteMapping("/{projectIdentifier}")
    public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectIdentifier){
        projectService.deleteProjectByIdentifier(projectIdentifier);

        return new ResponseEntity<String>("Project with ID: '"+projectIdentifier+"' was deleted.", HttpStatus.OK);
    }
}
