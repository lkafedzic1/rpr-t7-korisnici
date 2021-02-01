package ba.unsa.etf.rpr.t7;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class Controller {
    private KorisniciModel model;

    public ListView<Korisnik> lwKorisnici;
    public TextField fldIme;
    public TextField fldPrezime;
    public TextField fldEmail;
    public TextField fldUsername;
    public PasswordField pswPassword;

    public Button btnDodaj;
    public Button btnKraj;

    public Controller(KorisniciModel model) {
        this.model = model;
    }

    public void initialize() {
        lwKorisnici.setItems(model.getKorisnici());
        AtomicBoolean sveOk = new AtomicBoolean(true);

        model.trenutniKorisnikProperty().addListener((obs, o, n) -> {
            if (o != null) {
                fldIme.textProperty().unbindBidirectional(o.imeProperty());
                fldPrezime.textProperty().unbindBidirectional(o.prezimeProperty());
                fldEmail.textProperty().unbindBidirectional(o.emailProperty());
                fldUsername.textProperty().unbindBidirectional(o.usernameProperty());
                pswPassword.textProperty().unbindBidirectional(o.passwordProperty());
            }
            if (n == null) {
                fldIme.setText("");
                fldPrezime.setText("");
                fldEmail.setText("");
                fldUsername.setText("");
                pswPassword.textProperty().setValue("");
            }
            else {
                fldIme.textProperty().bindBidirectional(model.getTrenutniKorisnik().imeProperty());
                fldPrezime.textProperty().bindBidirectional(model.getTrenutniKorisnik().prezimeProperty());
                fldEmail.textProperty().bindBidirectional(model.getTrenutniKorisnik().emailProperty());
                fldUsername.textProperty().bindBidirectional(model.getTrenutniKorisnik().usernameProperty());
                pswPassword.textProperty().bindBidirectional(model.getTrenutniKorisnik().passwordProperty());
            }
        });

        lwKorisnici.getSelectionModel().selectedItemProperty().addListener((obs,o,n) -> {
            model.setTrenutniKorisnik(n);
            lwKorisnici.refresh();
        });

        fldIme.textProperty().addListener((obs, o, n) -> {
            if (fldIme.getText().trim().isEmpty()) {
                fldIme.getStyleClass().removeAll("poljeIspravno");
                fldIme.getStyleClass().add("poljeNijeIspravno");
            } else {
                fldIme.getStyleClass().removeAll("poljeNijeIspravno");
                fldIme.getStyleClass().add("poljeIspravno");
            }
        });
        fldPrezime.textProperty().addListener((obs, o, n) -> {
            if (fldPrezime.getText().trim().isEmpty()) {
                fldPrezime.getStyleClass().removeAll("poljeIspravno");
                fldPrezime.getStyleClass().add("poljeNijeIspravno");
            } else {
                fldPrezime.getStyleClass().removeAll("poljeNijeIspravno");
                fldPrezime.getStyleClass().add("poljeIspravno");
            }
        });
        fldEmail.textProperty().addListener((obs, o, n) -> {
            if (fldEmail.getText().trim().isEmpty()) {
                fldEmail.getStyleClass().removeAll("poljeIspravno");
                fldEmail.getStyleClass().add("poljeNijeIspravno");
            } else {
                fldEmail.getStyleClass().removeAll("poljeNijeIspravno");
                fldEmail.getStyleClass().add("poljeIspravno");
            }
        });
        pswPassword.textProperty().addListener((obs, o, n) -> {
            if (pswPassword.getText().trim().isEmpty()) {
                pswPassword.getStyleClass().removeAll("poljeIspravno");
                pswPassword.getStyleClass().add("poljeNijeIspravno");
            } else {
                pswPassword.getStyleClass().removeAll("poljeNijeIspravno");
                pswPassword.getStyleClass().add("poljeIspravno");
            }
        });
        fldUsername.textProperty().addListener((obs, o, n) -> {
            if (fldUsername.getText().trim().isEmpty()) {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
                sveOk.set(false);
            } else {
                fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
                fldUsername.getStyleClass().add("poljeIspravno");
            }
        });
    }

    public void actionDodaj() {
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(fldIme.getText());
        korisnik.setPrezime(fldPrezime.getText());
        korisnik.setEmail(fldEmail.getText());
        korisnik.setPassword(pswPassword.getText());
        korisnik.setUsername(fldUsername.getText());
        lwKorisnici.getItems().add(korisnik);
    }

    public void actionKraj(ActionEvent actionEvent) {
        Stage stage = (Stage) fldIme.getScene().getWindow();
        stage.close();
    }
}