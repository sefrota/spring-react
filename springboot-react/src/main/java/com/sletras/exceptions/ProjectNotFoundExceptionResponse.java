package com.sletras.exceptions;

/**
 * Created by sergioletras on 07/04/19.
 */
public class ProjectNotFoundExceptionResponse {
    private String projectIdentifier;

    public ProjectNotFoundExceptionResponse(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
