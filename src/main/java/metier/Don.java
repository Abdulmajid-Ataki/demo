package metier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Don {
    private int idDon;
    private double montant;
    private String typeDon;
    private Date dateDon;
    private int idUser;
    private int idProjet;


    public boolean isValid(){    return montant>0 && typeDon!=null && !typeDon.trim().isEmpty() && dateDon!=null;}



}
