package com.mooo.ewolvy.whendidi.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.mooo.ewolvy.whendidi.dummy.DummyContent;
import com.mooo.ewolvy.whendidi.model.TaskItem;

import java.util.ArrayList;
import java.util.List;

public class TasksViewModel extends ViewModel {
    private List<TaskItem> tasksList;

    private void loadDummyUsers(){
        tasksList = DummyContent.ITEMS;
    }

    public List<TaskItem> getAllTasks(){
        if (tasksList == null){
            loadDummyUsers();
        }
        return tasksList;
    }

    public List<TaskItem> getRemindTasks(){
        if (tasksList == null){
            loadDummyUsers();
        }
        List<TaskItem> remindedTasksList = new ArrayList<>();

        for (int i = 0; i < tasksList.size(); i++){
            if (!tasksList.get(i).getRemindOn().equals("NO")){
                remindedTasksList.add(tasksList.get(i));
            }
        }

        return remindedTasksList;
    }

    public TaskItem getItem(int position){
        if (tasksList == null){
            loadDummyUsers();
        }
        return tasksList.get(position);
    }

    public void deleteTask (int position){
        tasksList.remove(position);
    }
}
