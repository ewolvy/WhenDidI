package com.mooo.ewolvy.whendidi.dummy;

import com.mooo.ewolvy.whendidi.model.TaskItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeDataSource implements DataSourceInterface {

    private final String[] name = {
            "Cambiar aceite del coche",
            "Cambiar aceite de freir",
            "Limpiar filtro lavadora",
            "Limpiar filtro lavavajillas"
    };

    private final int[] color = {
            0xFFAAAAAA,
            0xFFAAAA00,
            0xFF00AAAA,
            0xFF309040
    };

    private final String[] lastTime = {
            "2018-05-05",
            "2017-06-04",
            "2016-07-03",
            "2015-08-02",
    };

    private final String[] remindOn = {
            "2019-07-05",
            "2020-06-04",
            "2021-07-03",
            "NO",
    };


    @Override
    public List<TaskItem> getAllData() {
        ArrayList<TaskItem> listOfData = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 12; i++){
            int randOne = random.nextInt(4);
            int randTwo = random.nextInt(4);
            int randThree = random.nextInt(4);
            int randFour = random.nextInt(4);

            TaskItem taskItem = new TaskItem(i,
                    name[randOne],
                    color[randTwo],
                    lastTime[randThree],
                    remindOn[randFour]);
            listOfData.add(taskItem);
        }

        return listOfData;
    }

    @Override
    public List<TaskItem> getNotRemindedData() {
        List<TaskItem> allData;
        List<TaskItem> filteredData = new ArrayList<>();

        allData = getAllData();
        for (int i = 0; i < allData.size(); i++){
            if (!allData.get(i).getRemindOn().equals("NO")){
                filteredData.add(allData.get(i));
            }
        }

        return filteredData;
    }

    @Override
    public TaskItem createNewListItem() {
        return null;
    }

    @Override
    public void deleteListItem(TaskItem listItem) {

    }

    @Override
    public void insertListItem(TaskItem temporaryListItem) {

    }
}
