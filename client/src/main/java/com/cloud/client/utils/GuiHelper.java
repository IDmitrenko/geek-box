package com.cloud.client.utils;

import com.cloud.common.utils.FileAbout;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

import javax.swing.table.TableColumn;
import javax.swing.text.TableView;

public class GuiHelper {
/*
    public static void prepareTableForFileAbout(TableView<FileAbout> tableView) {
        TableColumn<FileAbout, String> columnFileName = new TableColumn<>("Имя файла");
        columnFileName.setCellValueFactory(new PropertyValueFactory<FileAbout, String>("name"));
        columnFileName.setPrefWidth(200);

        TableColumn<FileAbout, String> columnFileSize = new TableColumn<>("Размер");
        columnFileSize.setCellValueFactory(param -> {
            long size = param.getValue().getSize();
            return new ReadOnlyObjectWrapper(String.valueOf(size) + " bytes");
        });
        columnFileSize.setPrefWidth(120);

        tableView.getColumns().addAll(columnFileName, columnFileSize);
    }

    public static void addToTableDragAndDropFilesAction(javafx.scene.control.TableView<FileAbout> filesTable, String rootPath, ResultAction resultAction) {
        filesTable.setOnDragOver(event -> {
            if (event.getGestureSource() != filesTable && event.getDragboard().hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        filesTable.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasFiles()) {
                for (int i = 0; i < db.getFiles().size(); i++) {
                    try {

                    }
                }
            }
        });
    }
*/
}
