package com.sletras.repositories;

import com.sletras.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sergioletras on 14/03/19.
 */
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

    Project findByProjectIdentifier(String projectIdentifier);

    @Override
    Iterable<Project> findAll();


}
