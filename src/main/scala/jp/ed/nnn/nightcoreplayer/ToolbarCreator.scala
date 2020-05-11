package jp.ed.nnn.nightcoreplayer

import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.Pos
import javafx.scene.Scene
//import javafx.scene.control.{Button, Label, TableView}
//import javafx.scene.image.{Image, ImageView}
//import javafx.scene.input.MouseEvent
import javafx.scene.control.{Label, TableView}
import javafx.scene.layout.HBox
import javafx.scene.media.MediaView
import javafx.stage.Stage
import javafx.util.Duration

import jp.ed.nnn.nightcoreplayer.SizeConstants._
import jp.ed.nnn.nightcoreplayer.ToolbarButtonCreator.createButton


object ToolbarCreator {

  //def create(mediaView: MediaView, tableView: TableView[Movie], timeLabel: Label, primaryStage: Stage): HBox = {
  def create(mediaView: MediaView, tableView: TableView[Movie], timeLabel: Label, scene: Scene, primaryStage: Stage): HBox = {
    val toolBar = new HBox()
    toolBar.setMinHeight(toolBarMinHeight)
    toolBar.setAlignment(Pos.CENTER)
    toolBar.setStyle("-fx-background-color: Black")

    // first button
    val firstButton = createButton("first.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit =
        if (mediaView.getMediaPlayer != null) {
          MoviePlayer.playPre(tableView, mediaView, timeLabel)
        }
    })

    // back button
    val backButton = createButton("back.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit =
        if (mediaView.getMediaPlayer != null) {
          mediaView.getMediaPlayer.seek(
            mediaView.getMediaPlayer.getCurrentTime.subtract(new Duration(10000)))
        }
    })

    // play button
    val playButton = createButton("play.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit = {
        val selectionModel = tableView.getSelectionModel
        if (mediaView.getMediaPlayer != null && !selectionModel.isEmpty) {
          mediaView.getMediaPlayer.play()
        }
      }
    })

    // pause button
    val pauseButton = createButton("pause.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit = {
        if (mediaView.getMediaPlayer != null) mediaView.getMediaPlayer.pause()
      }
    })

    // forward button
    val forwardButton = createButton("forward.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit =
        if (mediaView.getMediaPlayer != null) {
          //mediaView.getMediaPlayer.seek(mediaView.getMediaPlayer.getCurrentTime.add(new Duration(10000)))
          val player = mediaView.getMediaPlayer
          if (player.getTotalDuration.greaterThan(player.getCurrentTime.add(new Duration(10000)))) {
            player.seek(player.getCurrentTime.add(new Duration(10000)))
          }
        }
    })

    // last button
    val lastButton = createButton("last.png", new EventHandler[ActionEvent]() {
      override def handle(event: ActionEvent): Unit =
        if (mediaView.getMediaPlayer != null) {
          MoviePlayer.playNext(tableView, mediaView, timeLabel)
        }
    })

    // fullscreen button
    val fullscreenButton = createButton("fullscreen.png", new EventHandler[ActionEvent]() {
      //override def handle(event: ActionEvent): Unit =
      override def handle(event: ActionEvent): Unit = {
        primaryStage.setFullScreen(true)
        mediaView.fitHeightProperty().unbind()
        mediaView.fitHeightProperty().bind(scene.heightProperty())
        mediaView.fitWidthProperty().unbind()
        mediaView.fitWidthProperty().bind(scene.widthProperty())
      }
    })
    primaryStage.fullScreenProperty().addListener(new ChangeListener[java.lang.Boolean] {
      override def changed(observable: ObservableValue[_ <: java.lang.Boolean], oldValue: java.lang.Boolean, newValue: java.lang.Boolean): Unit =
        if (!newValue) {
          mediaView.fitHeightProperty().unbind()
          mediaView.fitWidthProperty().unbind()
          mediaView.fitHeightProperty().bind(scene.heightProperty().subtract(toolBarMinHeight))
          mediaView.fitWidthProperty().bind(scene.widthProperty().subtract(tableMinWidth))
        }
    })

    toolBar.getChildren.addAll(
      firstButton, backButton, playButton, pauseButton, forwardButton, lastButton, fullscreenButton, timeLabel)
    toolBar
  }

//  private[this] def createButton(imagePath: String, eventHandler: EventHandler[ActionEvent]): Button = {
//    val buttonImage = new Image(getClass.getResourceAsStream(imagePath))
//    val button = new Button()
//    button.setGraphic(new ImageView(buttonImage))
//    button.setStyle("-fx-background-color: Black")
//    button.setOnAction(eventHandler)
//    button.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        button.setStyle("-fx-body-color: Black")
//      }
//    })
//    button.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        button.setStyle("-fx-background-color: Black")
//      }
//    })
//    button
//  }

}