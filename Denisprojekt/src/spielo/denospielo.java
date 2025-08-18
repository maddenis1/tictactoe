package spielo;

import java.io.FileInputStream;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class denospielo extends Application {

    private int balance = 100; 
    private final Random random = new Random();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        // Slot-Bilder laden
        Image banana = new Image(new FileInputStream("src/assets/bild1.jpg"));
        Image cherries = new Image(new FileInputStream("src/assets/bild2.jpg"));
        Image seven = new Image(new FileInputStream("src/assets/bild3.jpg"));
        Image placeholder = new Image(new FileInputStream("src/assets/bild4.jpg"));

        Image[] slotImages = { banana, seven, cherries };

        // GUI-Komponenten
        Label lblCredits = new Label(getCreditText());
        lblCredits.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        ImageView[] reels = {
            createImageView(placeholder),
            createImageView(placeholder),
            createImageView(placeholder)
        };

        Button btnSpin = new Button("SPIN", new ImageView(new Image(new FileInputStream("src/assets/bild5.jpg"))));
        btnSpin.setTextFill(Color.WHITE);
        btnSpin.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        // Button-Event
        btnSpin.setOnAction(e -> {
            if (balance <= 5) { 
                btnSpin.setDisable(true);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Game Over!");
                alert.setContentText("Keine Credits mehr!\nNeues Spiel starten?");
                alert.showAndWait().filter(ButtonType.OK::equals).ifPresent(b -> {
                    balance = 100;
                    btnSpin.setDisable(false);
                    lblCredits.setText(getCreditText());
                });
                return;
            }

            balance -= 5;
            lblCredits.setText(getCreditText());

            int[] result = spinReels(reels, slotImages);
            checkWin(result, lblCredits);
        });

        // Layouts
        HBox hboxReels = new HBox(20, reels[0], reels[1], reels[2]);
        hboxReels.setAlignment(Pos.CENTER);

        VBox root = new VBox(20, lblCredits, hboxReels, btnSpin);
        root.setAlignment(Pos.CENTER);

        // Szene und Stage
        Scene scene = new Scene(root, 700, 500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Denis' Slot Machine");
        stage.getIcons().add(new Image(new FileInputStream("src/assets/bild6.jpg")));
        stage.show();
    }

    private ImageView createImageView(Image img) {
        ImageView view = new ImageView(img);
        view.setFitWidth(150);
        view.setFitHeight(150);
        return view;
    }

    private String getCreditText() {
        return "Credits: " + balance + " €";
    }

    private int[] spinReels(ImageView[] reels, Image[] slotImages) {
        int[] indexes = new int[reels.length];
        for (int i = 0; i < reels.length; i++) {
            int index = random.nextInt(slotImages.length);
            reels[i].setImage(slotImages[index]);
            indexes[i] = index;
        }
        return indexes;
    }

    private void checkWin(int[] result, Label lblCredits) {
        if (result[0] == result[1] && result[1] == result[2]) {
            int symbol = result[0];
            int prize = (symbol == 0) ? 10 : (symbol == 1) ? 20 : 50;
            balance += prize;
            lblCredits.setText(getCreditText());
        }
    }
}
