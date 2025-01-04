package dao;

import metier.Don;

import java.util.ArrayList;

public interface IDon {
    public void addDonation(Don donation);
    public ArrayList<Don> getAllDonations();
    public Don getDon(int id);
    public void deleteDonation(int id);
    public void updateDonation(Don donation);
}
