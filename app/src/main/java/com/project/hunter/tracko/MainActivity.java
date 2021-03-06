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
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.hunter.tracko.Model.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final Context context = this;
    final String taskListFile = "taskListFile";
    final String taskLogTag ="TASK";
    private List<Task> taskList = new ArrayList<Task>(5);
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
                final View addTaskView = layoutInflater.inflate(R.layout.add_task_dialog, null);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(addTaskView);

                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {

//                                        View view = findViewById(id);
//                                        EditText taskName = (EditText)addTaskView.findViewById(R.id.taskNameEdit);     //edit: delete
//                                        if(taskName == null)
//                                            Log.e("NO", "null fir se");
//                                        else
//                                            Log.d("good", taskName.getText().toString());
//                                        try {
                                            MainActivity.this.addNewTask(addTaskView);
//                                        }
//                                        catch(IOException e) {
//                                            Log.e("FATAL", "Exception encountered" + e.getMessage());
//                                        }
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

        //adding tasks to the homepage

//        String readS = readFromFile(this);
//        Toast.makeText(context, "String: " + readS, Toast.LENGTH_LONG).show();

//        FileInputStream fileInputStream = null;
//        ObjectInputStream objectinputstream = null;
//        taskList.clear();
//        try {
//            fileInputStream = new FileInputStream(taskListFile);
//            objectinputstream = new ObjectInputStream(fileInputStream);
//            Task readTask = null;
//            do {
//                readTask = (Task) objectinputstream.readObject();
//                if(readTask != null){
//                    taskList.add(readTask);
//                }
//            } while (readTask != null);
//            objectinputstream.close();
//            fileInputStream.close();
//        } catch (Exception e) {
//            Log.e(taskLogTag, "IO Exception during read");
//            e.printStackTrace();
//        }

        //////////////////////*********************////
//        Task newTask = new Task("run","km", 15, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
//        taskList.add(newTask);
//        newTask = new Task("read","books", 25, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
//        taskList.add(newTask);
//        newTask = new Task("shag","times", 100, LocalDateTime.now(), LocalDateTime.now().plusMinutes(45));
//        taskList.add(newTask);
        //////////////////////*********************////
        printTaskList();


//        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater vi = getLayoutInflater();
//        for(Task task: taskList) {
//            View childTaskView = vi.inflate(R.layout.task_list_layout, null);
//
//            TextView textView = (TextView) childTaskView.findViewById(R.id.taskEntryTextView);
//            textView.setText(task.getTaskDescription());
//// insert into main view
//        ViewGroup viewGroupForInsertion = (ViewGroup) findViewById(R.id.taskListViewGroup);
//            viewGroupForInsertion.addView(childTaskView);
//        }

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

    // custom methods
    private void addNewTask(View view) {//throws IOException {
//    private void addNewTask(View view) throws IOException {
        EditText taskName = (EditText) view.findViewById(R.id.taskNameEdit);
        EditText targetNumUnit = (EditText) view.findViewById(R.id.targetNumUnitEdit);
        EditText targetUnitType = (EditText) view.findViewById(R.id.targetUnitTypeEdit);
        EditText time = (EditText) view.findViewById(R.id.timeEdit);

        //edit: need to do type check
        if(taskName == null || targetNumUnit == null || targetUnitType == null || time == null) {
            if(taskName  == null)
                Log.e(taskLogTag, "null taskName detected");
            if(targetNumUnit  == null)
                Log.e(taskLogTag, "null targetNumUnit detected");
            if(targetUnitType  == null)
                Log.e(taskLogTag, "null targetUnitType detected");
            if(time  == null)
                Log.e(taskLogTag, "null time detected");
        }
        else{
            Log.d(taskLogTag, "null check ok");
        }

        LocalDateTime currentTime = LocalDateTime.now();
        Task newTask = new Task(taskName.getText().toString(),
                targetUnitType.getText().toString(),
                Long.parseLong(targetNumUnit.getText().toString()),
                currentTime,
                currentTime.plusMinutes(Long.parseLong(time.getText().toString())));
        Log.i(taskLogTag, "new task created");
        taskList.add(newTask);

        //create file entry for task
        int openMode = Context.MODE_PRIVATE;
        File file = new File(taskListFile);
        if(file.exists()) {
            Log.d(taskLogTag, "File already exists");
            openMode |= Context.MODE_APPEND;
        }
        else {
            Log.d(taskLogTag, "Need to create file");
        }
//        FileOutputStream fos = null;
//        ObjectOutputStream os = null;
//        try {
//            if(!file.exists())
//                file.createNewFile();
//            fos = context.openFileOutput(taskListFile, openMode);
//            os = new ObjectOutputStream(fos);
//            os.writeObject(newTask);
//            os.close();
//            fos.close();
//        } catch (IOException e) {
//            Log.e(taskLogTag, "IO Exception");
//            e.printStackTrace();
//        }

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write("hello there");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }

//        String readS = readFromFile(this);
//        Toast.makeText(context, "String: " + readS, Toast.LENGTH_LONG).show();


//        printTaskList();
//        Toast.makeText(context, Integer.toString(taskList.size()), Toast.LENGTH_LONG).show();
    }

    private String readFromFile(Context context) {      //edit: chori wala function delete

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
    
    //debug methods
    private void printTaskList() {
        for (Task task : taskList) {
            task.printInfo();
        }
    }
}
