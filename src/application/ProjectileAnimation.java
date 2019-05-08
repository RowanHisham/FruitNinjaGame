package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class ProjectileAnimation {
    private Node node;
    private Timeline timeline;
    private double maxX, maxY;
    private double initialVx, initialVy;
    private double duration;
    private double t = 0;
    private double g;

    private EventHandler<ActionEvent> onFinished;

    public ProjectileAnimation(Node node) {
        this(node, 1196, 747);
    }
    public ProjectileAnimation(Node node, double parentWidth, double parentHeight) {
        this.node = node;
        this.maxX = parentWidth;
        this.maxY = parentHeight;
        this.g = parentHeight*1.5;
        KeyFrame keyFrame = new KeyFrame(Duration.millis(16), (ActionEvent) -> move());
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        doMafs();
    }
    private void doMafs() {
        node.setLayoutX(Math.random()*0.6*maxX+0.2*maxX);
        node.setLayoutY(maxY);
        double y = Math.random()*maxY*0.2+maxY*0.75;
        initialVy = Math.sqrt(2*g*y);
        duration = Duration.seconds(2 * Math.abs(initialVy / g)).toSeconds();
        double layoutX = node.getLayoutX();
        int sign;
        if(layoutX < maxX/2)
            sign = -1;
        else if(layoutX > maxX/2)
            sign = 1;
        else {
            sign = (Math.random()>0.5 ? 1: -1);
        }
        double x = Math.random()*(maxX*(sign==1?0:1)+sign*layoutX)*0.8*sign;
        initialVx = x/duration;
    }

    public void play() {
        timeline.play();
    }


    public void stop() {
        timeline.stop();
    }

    public void setRate(double rate) {
        timeline.setRate(rate);
    }

    private void move() {
        t += timeline.getCycleDuration().toSeconds();
        node.setTranslateX(-initialVx * t);
        double dy = initialVy * t - 0.5 * g * t * t;
        node.setTranslateY(-dy);
        if (t >= duration) {
            node.setTranslateY(maxY);
            stop();
            t = 0;
            if(onFinished != null)
                onFinished.handle(new ActionEvent(node, null));
        }
    }

    public void setOnFinished(EventHandler<ActionEvent> onFinished) {
        this.onFinished = onFinished;
    }
}
