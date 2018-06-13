package com.mooo.ewolvy.whendidi.view;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mooo.ewolvy.whendidi.R;
import com.mooo.ewolvy.whendidi.model.TaskItem;
import com.mooo.ewolvy.whendidi.viewmodel.TasksViewModel;

/**
 * A fragment representing a single Task detail screen.
 * This fragment is either contained in a {@link TaskListActivity}
 * in two-pane mode (on tablets) or a {@link TaskDetailActivity}
 * on handsets.
 */
public class TaskDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private TaskItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            TasksViewModel tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
            mItem = tasksViewModel.getItemById(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            assert activity != null;
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.task_detail, container, false);
        EditText editText = rootView.findViewById(R.id.txtEditName);
        editText.setText(mItem.getName());
        editText = rootView.findViewById(R.id.txtEditLastDate);
        editText.setText(mItem.getLastDate());
        Button button = rootView.findViewById(R.id.btnEditColor);
        button.setBackgroundColor(mItem.getColor());
        editText = rootView.findViewById(R.id.txtEditReminder);
        editText.setText(mItem.getRemindOn());

        editText = rootView.findViewById(R.id.txtEditHistoric);
        editText.setText(mItem.getDatesHistory());
        return rootView;
    }
}
