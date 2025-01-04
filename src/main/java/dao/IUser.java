package dao;

import metier.User;

import java.util.ArrayList;

public interface IUser {
    public void add(User user);
    public User getUser(int idUser);
    public ArrayList<User> getAllUsers();
    public void deleteUser(int idUser);
    public void updateUser(User user);
}
