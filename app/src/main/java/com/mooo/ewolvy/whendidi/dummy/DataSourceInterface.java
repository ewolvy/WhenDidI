package com.mooo.ewolvy.whendidi.dummy;

import com.mooo.ewolvy.whendidi.model.TaskItem;

import java.util.List;

public interface DataSourceInterface {
    List<TaskItem> getAllData();
    List<TaskItem> getNotRemindedData();

    TaskItem createNewListItem();

    void deleteListItem(TaskItem listItem);

    void insertListItem(TaskItem temporaryListItem);
}
