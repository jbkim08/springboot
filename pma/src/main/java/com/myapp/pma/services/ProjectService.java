package com.myapp.pma.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.pma.dao.ProjectRepository;
import com.myapp.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public void save(Project project) {
		projectRepository.save(project);
	}

	public Project findByProjectId(long id) {
		return projectRepository.findByProjectId(id);
	}

	public void deleteProject(long id) {
		projectRepository.deleteById(id);
	}

	public void update(@Valid Project project) {
		Project pro = projectRepository.findByProjectId(project.getProjectId());
		pro.setName(project.getName());
		pro.setStage(project.getStage());
		pro.setDescription(project.getDescription());
		pro.setEmployees(project.getEmployees());
		
		projectRepository.save(pro);	
	}	
}
