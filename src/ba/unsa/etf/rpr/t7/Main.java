package ba.unsa.etf.rpr.t7;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        KorisniciModel model = new KorisniciModel();
        model.napuni();
        Controller controller = new Controller(model);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/korisnici.fxml"));
        loader.setController(controller);
        Parent root = loader.load();

        primaryStage.setTitle("Korisnici");
        primaryStage.setScene(new Scene(root, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
