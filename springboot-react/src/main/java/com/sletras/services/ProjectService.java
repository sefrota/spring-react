package com.sletras.services;

import com.sletras.domain.Project;
import com.sletras.exceptions.ProjectIdException;
import com.sletras.exceptions.ProjectNotFoundException;
import com.sletras.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sergioletras on 14/03/19.
 */
@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project){

        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        try{
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project Id '"+ project.getProjectIdentifier().toUpperCase()+"' already exists");
        }
    }

    public Project findProjectByIdentifier(String projectIdentifier){

        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if(project == null)
            throw new ProjectNotFoundException("Project with Id '"+ projectIdentifier +"' does not exist");

        return project;
    }

    public Iterable<Project> findAllProjects(){

        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());

        if(project == null)
            throw new ProjectNotFoundException("Project with Id '"+ projectIdentifier +"' does not exist");

        projectRepository.delete(project);
    }
}
