package web;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeleUser {

    private int idUser;
    private String nomUser;
    private String prenomUser;
    private String email;
    private String telephone;
    private String adresse;
    private String password;
    private String role;

}
