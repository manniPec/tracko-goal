package com.project.hunter.tracko;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.project.hunter.tracko.Model.Task;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View addTaskView = layoutInflater.inflate(R.layout.add_task_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(addTaskView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        EditText taskName = (EditText) findViewById(R.id.taskNameEdit);     //edit: delete
                                        if(taskName == null)
                                            Log.e("NO", "null fir se");
                                        else
                                            Log.d("good", taskName.getText().toString());
                                        MainActivity.this.addNewTask();
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void addNewTask() {
        EditText taskName = (EditText) findViewById(R.id.taskNameEdit);
        EditText targetNumUnit = (EditText) findViewById(R.id.targetNumUnitEdit);
        EditText targetUnitType = (EditText) findViewById(R.id.targetUnitTypeEdit);
        EditText time = (EditText) findViewById(R.id.timeEdit);

        //edit: need to do type check
        if(taskName == null || targetNumUnit == null || targetUnitType == null || time == null) {
            if(taskName  == null)
                Log.e("TASK", "null taskName detected");
            if(targetNumUnit  == null)
                Log.e("TASK", "null targetNumUnit detected");
            if(targetUnitType  == null)
                Log.e("TASK", "null targetUnitType detected");
            if(time  == null)
                Log.e("TASK", "null time detected");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        Task newTask = new Task(taskName.getText().toString(),
                targetUnitType.getText().toString(),
                Long.parseLong(targetNumUnit.getText().toString()),
                currentTime,
                currentTime.plusMinutes(Long.parseLong(time.getText().toString())));
        Log.i("TASK", "new task created");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
