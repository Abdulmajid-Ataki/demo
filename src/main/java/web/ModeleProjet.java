package web;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeleProjet {
    private int idProjet;
    private String nomProjet;
    private String descriptionProjet;
    private double financialGoal;
    private double fundsRaised;
    private Date dateDebut;
    private Date dateFin;
    private int idAssociation;

}

