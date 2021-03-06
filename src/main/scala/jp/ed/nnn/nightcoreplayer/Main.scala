package jp.ed.nnn.nightcoreplayer

//import java.io.File
import javafx.application.Application
//import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.FXCollections
//import javafx.event.EventHandler
//import javafx.event.{ActionEvent, EventHandler}
// javafx.geometry.Pos
import javafx.event.EventHandler
import javafx.scene.Scene
//import javafx.scene.layout.BorderPane
//import javafx.scene.control.Label
import javafx.scene.control.cell.PropertyValueFactory
//import javafx.scene.control.{Label, TableColumn, TableView}
//import javafx.scene.input.{DragEvent, TransferMode}
//import javafx.scene.layout.{BorderPane, HBox}
//import javafx.scene.control.{Label, TableColumn, TableRow, TableView}
import javafx.scene.control._
//import javafx.scene.image.{Image, ImageView}
//import javafx.scene.input.{DragEvent, MouseEvent, TransferMode}
//import javafx.scene.media.{Media, MediaPlayer, MediaView}
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.media.MediaView
import javafx.scene.paint.Color
import javafx.stage.Stage
//import javafx.util.Duration
//import javafx.util.{Callback, Duration}
import javafx.util.Callback
import jp.ed.nnn.nightcoreplayer.SizeConstants._

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
//  private[this] val mediaViewFitWidth = 800
//  private[this] val mediaViewFitHeight = 450
//  private[this] val toolBarMinHeight = 50
//  private[this] val tableMinWidth = 300

  override def start(primaryStage: Stage): Unit = {
    //    val path = "/Users/CAT/Downloads/video.mp4"
    //    val media = new Media(new File(path).toURI.toString)
    //    val mediaPlayer = new MediaPlayer(media)
    //    mediaPlayer.setRate(1.25)
    //    mediaPlayer.play()
    //    val mediaView = new MediaView(mediaPlayer)
    //mediaView.setFitWidth(800)
    //mediaView.setFitHeight(450)
    val mediaView = new MediaView()

    val timeLabel = new Label()
    //    mediaPlayer.currentTimeProperty().addListener(new ChangeListener[Duration] {
    //      override def changed(observable: ObservableValue[_ <: Duration], oldValue: Duration, newValue: Duration): Unit =
    //        timeLabel.setText(formatTime(mediaPlayer.getCurrentTime, mediaPlayer.getTotalDuration))
    //    })
    //    mediaPlayer.setOnReady(new Runnable {
    //      override def run(): Unit =
    //        timeLabel.setText(formatTime(mediaPlayer.getCurrentTime, mediaPlayer.getTotalDuration))
    //    })

    timeLabel.setText("00:00:00/00:00:00")
    timeLabel.setTextFill(Color.WHITE)
    //val toolBar = new HBox(timeLabel)
//    val toolBar = new HBox()
//    toolBar.setMinHeight(toolBarMinHeight)
//    toolBar.setAlignment(Pos.CENTER)
//    toolBar.setStyle("-fx-background-color: Black")

    val tableView = new TableView[Movie]()
    tableView.setMinWidth(tableMinWidth)
    val movies = FXCollections.observableArrayList[Movie]()
    tableView.setItems(movies)
    tableView.setRowFactory(new Callback[TableView[Movie], TableRow[Movie]]() {
     override def call(param: TableView[Movie]): TableRow[Movie] = {
       val row = new TableRow[Movie]()
       row.setOnMouseClicked(new EventHandler[MouseEvent] {
         override def handle(event: MouseEvent): Unit = {
           if (event.getClickCount >= 1 && !row.isEmpty) {
             //playMovie(row.getItem, mediaView, timeLabel)
             //playMovie(row.getItem, tableView, mediaView, timeLabel)
             MoviePlayer.play(row.getItem, tableView, mediaView, timeLabel)
           }
         }
       })
       row
     }
    })

    val fileNameColumn = new TableColumn[Movie, String]("ファイル名")
    fileNameColumn.setCellValueFactory(new PropertyValueFactory("fileName"))
    fileNameColumn.setPrefWidth(160)
    val timeColumn = new TableColumn[Movie, String]("時間")
    timeColumn.setCellValueFactory(new PropertyValueFactory("time"))
    timeColumn.setPrefWidth(80)
    val deleteActionColumn = new TableColumn[Movie, Long]("削除")
    deleteActionColumn.setCellValueFactory(new PropertyValueFactory("id"))
    deleteActionColumn.setPrefWidth(60)
    deleteActionColumn.setCellFactory(new Callback[TableColumn[Movie, Long], TableCell[Movie, Long]]() {
      override def call(param: TableColumn[Movie, Long]): TableCell[Movie, Long] = {
        new DeleteCell(movies, mediaView, tableView)
      }
    })

    //tableView.getColumns.setAll(fileNameColumn, timeColumn)
    tableView.getColumns.setAll(fileNameColumn, timeColumn, deleteActionColumn)
    tableView.setPlaceholder(new Label("mp4ファイルをドラッグ&ドロップしてください"));
    // first button
//    val firstButtonImage = new Image(getClass.getResourceAsStream("first.png"))
//    val firstButton = new Button()
//    firstButton.setGraphic(new ImageView(firstButtonImage))
//    firstButton.setStyle("-fx-background-color: Black")
//    firstButton.setOnAction(new EventHandler[ActionEvent]() {
//    val firstButton = createButton("first.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit =
//        if (mediaView.getMediaPlayer != null) {
//          playPre(tableView, mediaView, timeLabel)
//        }
//    })
//    firstButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        firstButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    firstButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        firstButton.setStyle("-fx-background-color: Black")
//      }
//    })

    // back button
//    val backButtonImage = new Image(getClass.getResourceAsStream("back.png"))
//    val backButton = new Button()
//    backButton.setGraphic(new ImageView(backButtonImage))
//    backButton.setStyle("-fx-background-color: Black")
//    backButton.setOnAction(new EventHandler[ActionEvent]() {
//    val backButton = createButton("back.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit =
//        if (mediaView.getMediaPlayer != null) {
//          mediaView.getMediaPlayer.seek(
//            mediaView.getMediaPlayer.getCurrentTime.subtract(new Duration(10000)))
//        }
//    })
//    backButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        backButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    backButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        backButton.setStyle("-fx-background-color: Black")
//      }
//    })

    // play button
//    val playButtonImage = new Image(getClass.getResourceAsStream("play.png"))
//    val playButton = new Button()
//    playButton.setGraphic(new ImageView(playButtonImage))
//    playButton.setStyle("-fx-background-color: Black")
//    playButton.setOnAction(new EventHandler[ActionEvent]() {
//      val playButton = createButton("play.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit = {
//        val selectionModel = tableView.getSelectionModel
//        if (mediaView.getMediaPlayer != null && !selectionModel.isEmpty) {
//          mediaView.getMediaPlayer.play()
//        }
//      }
//    })
//    playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        playButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    playButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        playButton.setStyle("-fx-background-color: Black")
//      }
//    })

    // pause button
//    val pauseButtonImage = new Image(getClass.getResourceAsStream("pause.png"))
//    val pauseButton = new Button()
//    pauseButton.setGraphic(new ImageView(pauseButtonImage))
//    pauseButton.setStyle("-fx-background-color: Black")
//    pauseButton.setOnAction(new EventHandler[ActionEvent]() {
//    val pauseButton = createButton("pause.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit = {
//        if (mediaView.getMediaPlayer != null) mediaView.getMediaPlayer.pause()
//      }
//    })
//    pauseButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        pauseButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    pauseButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        pauseButton.setStyle("-fx-background-color: Black")
//      }
//    })

    //toolBar.getChildren.addAll(playButton, pauseButton, timeLabel)

    // forward button
//    val forwardButtonImage = new Image(getClass.getResourceAsStream("forward.png"))
//    val forwardButton = new Button()
//    forwardButton.setGraphic(new ImageView(forwardButtonImage))
//    forwardButton.setStyle("-fx-background-color: Black")
//    forwardButton.setOnAction(new EventHandler[ActionEvent]() {
//    val forwardButton = createButton("forward.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit =
//        if (mediaView.getMediaPlayer != null) {
//          mediaView.getMediaPlayer.seek(
//            mediaView.getMediaPlayer.getCurrentTime.add(new Duration(10000)))
//        }
//    })
//    forwardButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        forwardButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    forwardButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        forwardButton.setStyle("-fx-background-color: Black")
//      }
//    })

    // last button
//    val lastButtonImage = new Image(getClass.getResourceAsStream("last.png"))
//    val lastButton = new Button()
//    lastButton.setGraphic(new ImageView(lastButtonImage))
//    lastButton.setStyle("-fx-background-color: Black")
//    lastButton.setOnAction(new EventHandler[ActionEvent]() {
//    val lastButton = createButton("last.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit =
//        if (mediaView.getMediaPlayer != null) {
//          playNext(tableView, mediaView, timeLabel)
//        }
//    })
//    lastButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        lastButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    lastButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        lastButton.setStyle("-fx-background-color: Black")
//      }
//    })

    // fullscreen button
//    val fullscreenButtonImage = new Image(getClass.getResourceAsStream("fullscreen.png"))
//    val fullscreenButton = new Button()
//    fullscreenButton.setGraphic(new ImageView(fullscreenButtonImage))
//    fullscreenButton.setStyle("-fx-background-color: Black")
//    fullscreenButton.setOnAction(new EventHandler[ActionEvent]() {
//    val fullscreenButton = createButton("fullscreen.png", new EventHandler[ActionEvent]() {
//      override def handle(event: ActionEvent): Unit =
//        primaryStage.setFullScreen(true)
//    })
//    fullscreenButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        fullscreenButton.setStyle("-fx-body-color: Black")
//      }
//    })
//    fullscreenButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler[MouseEvent]() {
//      override def handle(event: MouseEvent): Unit = {
//        fullscreenButton.setStyle("-fx-background-color: Black")
//      }
//    })

    //toolBar.getChildren.addAll(backButton, playButton, pauseButton, forwardButton, timeLabel)
    //toolBar.getChildren.addAll(firstButton, backButton, playButton, pauseButton, forwardButton, lastButton, timeLabel)

    // toolBar.getChildren.addAll(firstButton, backButton, playButton, pauseButton, forwardButton, lastButton, fullscreenButton, timeLabel)
    //val toolBar = ToolbarCreator.create(mediaView, tableView, timeLabel, primaryStage)
    // TODO 後で消す
    //movies.addAll(Movie(1L, "movie.mp4", "00:00:00", "./movie.mp4", null))

    val baseBorderPane = new BorderPane()
    val scene = new Scene(baseBorderPane, mediaViewFitWidth + tableMinWidth, mediaViewFitHeight + toolBarMinHeight)
    val toolBar = ToolbarCreator.create(mediaView, tableView, timeLabel, scene, primaryStage)

    baseBorderPane.setStyle("-fx-background-color: Black")
    baseBorderPane.setCenter(mediaView)
    baseBorderPane.setBottom(toolBar)
    baseBorderPane.setRight(tableView)


    //val scene = new Scene(baseBorderPane, 800, 500) // 可変に対応
    //val scene = new Scene(baseBorderPane, mediaViewFitWidth, mediaViewFitHeight + toolBarMinHeight)
    //val scene = new Scene(baseBorderPane, mediaViewFitWidth + tableMinWidth, mediaViewFitHeight + toolBarMinHeight)
    scene.setFill(Color.BLACK)
    //mediaView.fitWidthProperty().bind(scene.widthProperty())
    mediaView.fitWidthProperty().bind(scene.widthProperty().subtract(tableMinWidth))
    mediaView.fitHeightProperty().bind(scene.heightProperty().subtract(toolBarMinHeight))

//    scene.setOnDragOver(new EventHandler[DragEvent] {
//     override def handle(event: DragEvent): Unit = {
//       if (event.getGestureSource != scene &&
//         event.getDragboard.hasFiles) {
//         event.acceptTransferModes(TransferMode.COPY_OR_MOVE: _*)
//       }
//       event.consume()
//     }
//    })
//
//    scene.setOnDragDropped(new EventHandler[DragEvent] {
//     override def handle(event: DragEvent): Unit = {
//       val db = event.getDragboard
//       if (db.hasFiles) {
//         db.getFiles.toArray(Array[File]()).toSeq.foreach { f =>
//             val filePath = f.getAbsolutePath
//             val fileName = f.getName
//             val media = new Media(f.toURI.toString)
////             val time = formatTime(media.getDuration)
////             val movie = Movie(System.currentTimeMillis(), fileName, time, filePath, media)
////             while (movies.contains(movie)) {
////               movie.setId(movie.getId + 1L)
////             }
////             movies.add(movie)
//            val player = new MediaPlayer(media)
//            player.setOnReady(new Runnable {
//              override def run(): Unit = {
//                val time = formatTime(media.getDuration)
//                val movie = Movie(System.currentTimeMillis(), fileName, time, filePath, media)
//                while (movies.contains(movie)) {
//                  movie.setId(movie.getId + 1L)
//                }
//                movies.add(movie)
//                player.dispose()
//              }
//            })
//           }
//     }
//       event.consume()
//     }
//    })
    scene.setOnDragOver(new MovieFileDragOverEventHandler(scene))
    scene.setOnDragDropped(new MovieFileDragDroppedEventHandler(movies))

    primaryStage.setTitle("NightcorePlayer")

    primaryStage.setScene(scene)
    primaryStage.show()
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

  //private[this] def playMovie(movie: Movie, mediaView: MediaView, timeLabel: Label): Unit = {
//  private[this] def playMovie(movie: Movie,tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = {
//    if (mediaView.getMediaPlayer != null) {
//      val oldPlayer = mediaView.getMediaPlayer
//      oldPlayer.stop()
//      oldPlayer.dispose()
//    }
//
//    val mediaPlayer = new MediaPlayer(movie.media)
//    mediaPlayer.currentTimeProperty().addListener(new ChangeListener[Duration] {
//      override def changed(observable: ObservableValue[_ <: Duration], oldValue: Duration, newValue: Duration): Unit =
//        timeLabel.setText(formatTime(mediaPlayer.getCurrentTime, mediaPlayer.getTotalDuration))
//    })
//    mediaPlayer.setOnReady(new Runnable {
//      override def run(): Unit =
//        timeLabel.setText(formatTime(mediaPlayer.getCurrentTime, mediaPlayer.getTotalDuration))
//    })
//    mediaPlayer.setOnEndOfMedia(new Runnable {
//      override def run(): Unit = playNext(tableView, mediaView, timeLabel)
//    })
//
//    mediaView.setMediaPlayer(mediaPlayer)
//    mediaPlayer.setRate(1.25)
//    mediaPlayer.play()
//  }

//  private[this] def playPre(tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = {
//    val selectionModel = tableView.getSelectionModel
//    if (selectionModel.isEmpty) return
//    val index = selectionModel.getSelectedIndex
//    val preIndex = (tableView.getItems.size() + index - 1) % tableView.getItems.size()
//    selectionModel.select(preIndex)
//    val movie = selectionModel.getSelectedItem
//    playMovie(movie, tableView, mediaView, timeLabel)
//  }
//  sealed trait Track
//  object Pre extends Track
//  object Next extends Track

  //private[this] def playNext(tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = {
//  private[this] def playAt(track: Track, tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = {
//    val selectionModel = tableView.getSelectionModel
//    if (selectionModel.isEmpty) return
//    val index = selectionModel.getSelectedIndex
//    //val nextIndex = (index + 1) % tableView.getItems.size()
//    //selectionModel.select(nextIndex)
//    val changedIndex = track match {
//      case Pre => (tableView.getItems.size() + index - 1) % tableView.getItems.size()
//      case Next => (index + 1) % tableView.getItems.size()
//    }
//    selectionModel.select(changedIndex)
//    val movie = selectionModel.getSelectedItem
//    playMovie(movie, tableView, mediaView, timeLabel)
//  }

//  private[this] def playPre(tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = playAt(Pre, tableView, mediaView, timeLabel)
//  private[this] def playNext(tableView: TableView[Movie], mediaView: MediaView, timeLabel: Label): Unit = playAt(Next, tableView, mediaView, timeLabel)

//  private[this] def formatTime(elapsed: Duration, duration: Duration): String = { "%02d:%02d:%02d/%02d:%02d:%02d".format(
//    private[this] def formatTime(elapsed: Duration): String = {"%02d:%02d:%02d".format(
//      elapsed.toHours.toInt,
//      elapsed.toMinutes.toInt % 60,
////      elapsed.toSeconds.toInt % 60,
////      duration.toHours.toInt,
////      duration.toMinutes.toInt % 60,
////      duration.toSeconds.toInt % 60)
//      elapsed.toSeconds.toInt % 60
//    )
//  }
//  private[this] def formatTime(elapsed: Duration, duration: Duration): String = s"${formatTime(elapsed)}/${formatTime(duration)}"
}
