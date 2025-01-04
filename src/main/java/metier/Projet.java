package metier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    private int idProjet ;
    private String nomProjet ;
    private String descriptionProjet ;
    private double financialGoal ;
    private double fundsRaised ;
    private Date dateDebut ;
    private Date dateFin ;


    public int getId() {
        return idProjet;
    }

    public void setId(int id) {
        this.idProjet = id;
    }

    public String getName() {
        return nomProjet;
    }

    public void setName(String name) {
        this.nomProjet = name;
    }

    public String getDescription() {
        return descriptionProjet;
    }

    public void setDescription(String description) {
        this.descriptionProjet = description;
    }
    public boolean isValid() {
        return nomProjet != null && !nomProjet.trim().isEmpty() &&
                descriptionProjet != null && !descriptionProjet.trim().isEmpty() &&
                financialGoal > 0 && fundsRaised >= 0 &&
                dateDebut != null && dateFin != null && dateFin.after(dateDebut);
    }
    public String toString() {
        return "Projet {" +
                "id=" + idProjet +
                ", nom='" + nomProjet +" "+
                ", description='" + descriptionProjet +" " +
                ", objectifFinancier=" + financialGoal +" "+
                ", fondsLeves=" + fundsRaised +" "+
                ", dateDebut=" + dateDebut +" "+
                ", dateFin=" + dateFin + '}';
    }



}
