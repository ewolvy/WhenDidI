package com.mooo.ewolvy.whendidi.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mooo.ewolvy.whendidi.R;
import com.mooo.ewolvy.whendidi.model.TaskItem;
import com.mooo.ewolvy.whendidi.viewmodel.TasksViewModel;

import java.util.List;

/**
 * An activity representing a list of Tasks. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link TaskDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class TaskListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.task_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        RecyclerView recyclerView = findViewById(R.id.task_list);
        assert recyclerView != null;
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        TasksViewModel tasksViewModel =
                ViewModelProviders.of(this).get(TasksViewModel.class);

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, tasksViewModel.getRemindTasksList(), mTwoPane));
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final TaskListActivity mParentActivity;
        private final List<TaskItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaskItem item = (TaskItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(TaskDetailFragment.ARG_ITEM_ID, item.getId());
                    TaskDetailFragment fragment = new TaskDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.task_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, TaskDetailActivity.class);
                    intent.putExtra(TaskDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(TaskListActivity parent,
                                      List<TaskItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.task_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
            holder.mNameView.setText(mValues.get(position).getName());
            holder.mDateReminder.setText(mValues.get(position).getRemindOn());
            holder.mLastTime.setText(mValues.get(position).getLastTime());
            holder.mView.setBackgroundColor(mValues.get(position).getColor());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mNameView;
            final TextView mDateReminder;
            final TextView mLastTime;
            final View mView;

            ViewHolder(View view) {
                super(view);
                mNameView = view.findViewById(R.id.lbl_task_name);
                mDateReminder = view.findViewById(R.id.lbl_date_reminder);
                mLastTime = view.findViewById(R.id.lbl_last_date);
                mView = view.findViewById(R.id.item_root);
            }
        }
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        /*First Param is for Up/Down motion, second is for Left/Right.
        Note that we can supply 0, one constant (e.g. ItemTouchHelper.LEFT), or two constants (e.g.
        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) to specify what directions are allowed.
        */

        return new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            // Dragged up or down
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            // Swiped left or right
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {

            }
        };
    }
}
