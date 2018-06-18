package com.mooo.ewolvy.whendidi.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;

import com.mooo.ewolvy.whendidi.dummy.DummyContent;
import com.mooo.ewolvy.whendidi.model.TaskItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date remindDate = null;

        for (int i = 0; i < tasksList.size(); i++){
            if (!tasksList.get(i).getRemindOn().equals("NO")){
                try {
                    remindDate = simpleDateFormat.parse(tasksList.get(i).getRemindOn());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (new Date().after(remindDate) && remindDate != null) {
                    remindedTasksList.add(tasksList.get(i));
                }
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

    public TaskItem getItemById (String id){
        if (tasksList == null){
            loadDummyUsers();
        }

        for (int i = 0; i < tasksList.size(); i++){
            if (tasksList.get(i).getId().equals(id)){
                return tasksList.get(i);
            }
        }
        return null;
    }

    public void deleteTask (int position){
        tasksList.remove(position);
    }
}
