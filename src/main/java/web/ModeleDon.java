package web;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class  ModeleDon {
    private int idDon;
    private double montant;
    private String typeDon;
    private Date dateDon;
    private int idUser;
    private int idProjet;
    private int idAssociation;



}

