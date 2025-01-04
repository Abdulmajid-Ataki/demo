package dao;

import metier.Projet;

import java.util.ArrayList;

public interface IProjet {
    public void addProject(Projet project);
    public Projet getProject(int id);
    public ArrayList<Projet> getAllProjects();
    public void deleteProject(int id);
    public void updateProject(Projet project);

}
