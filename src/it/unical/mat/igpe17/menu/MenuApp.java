package it.unical.mat.igpe17.menu;

import java.util.Arrays;
import java.util.List;

import it.unical.mat.igpe17.editor.MainEditor;
import it.unical.mat.igpe17.gui.MainGame;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

public class MenuApp extends Application {

    private static final int WIDTH = 1200;
    private static final int HEIGHT = 500;

    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Single Player", () -> { MainGame.main(); Platform.exit(); }),
            new Pair<String, Runnable>("Scoreboard", () -> {}),
            new Pair<String, Runnable>("Level Editor", () -> { new MainEditor(); Platform.exit(); }),
            new Pair<String, Runnable>("Credits", () -> {}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    private Parent createContent() {
       addBackground();
       addTitle();

       double lineX = WIDTH / 3 - 40;
       double lineY = HEIGHT / 3 + 235;

       addLine(lineX, lineY);
       addMenu(lineX + 5, lineY + 5);

       startAnimation();

       return root;
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("/bg4.png").toExternalForm()));
        imageView.setFitWidth(0);
        imageView.setFitHeight(0);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        MenuTitle title = new MenuTitle("Rock To Hell");
        title.setTranslateX(WIDTH / 7);
        title.setTranslateY(HEIGHT / 4);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 185);
        line.setStrokeWidth(2);
        line.setStroke(Color.color(1, 1, 1, 0.80));
        line.setEffect(new DropShadow(2, Color.RED));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(3), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(3.7 + i * 0.40), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
            MenuItem item = new MenuItem(data.getKey());
            item.setOnAction(data.getValue());
            item.setTranslateX(-300);

            Rectangle clip = new Rectangle(300, 30);
            clip.translateXProperty().bind(item.translateXProperty().negate());

            item.setClip(clip);

            menuBox.getChildren().addAll(item);
        });

        root.getChildren().add(menuBox);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    	Scene scene = new Scene(createContent());

        final MediaPlayer mediaPlayer = new MediaPlayer(new Media(getClass().getResource("/sound.mp3").toString()));
        mediaPlayer.play();

        primaryStage.setTitle("ROCK TO HELL");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
    	
        launch(args);
    }
}