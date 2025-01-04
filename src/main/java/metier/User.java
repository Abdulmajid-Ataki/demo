package metier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private String telephone;
    private String adresse;
    private Date dateInscription;
    private Role role;


    public enum Role {
        ADMIN, DONATEUR, GESTIONNAIRE
    }
    public boolean isValid() {
        return nomUser != null && !nomUser.trim().isEmpty() &&
                prenomUser != null && !prenomUser.trim().isEmpty() &&
                email != null && email.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$") &&
                telephone != null && telephone.matches("^\\+?[0-9]{10,15}$");
    }

    public String toString() {
        return "User {" +
                "id=" + idUser +" "+
                ",nom='" + nomUser +" "+
                ",prenom=" + prenomUser +" " +
                ",email='" + email +" " +
                ",telephone='" + telephone + " " +
                ",adresse='" + adresse +" " +
                ",role=" + role +
                '}';
    }
}





