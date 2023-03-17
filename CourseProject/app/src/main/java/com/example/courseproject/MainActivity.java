package com.example.courseproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView course_text_view;
    private static final String KEY_INDEX = "index";
    private TextView course_total_fees_text_view;
    private int currentIndex = 0;

    private static final String TAG = "Course Project";
    private Course[] allCourses;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Course.credits = 3;
        //Data structure Array
        allCourses = new Course[]{
                new Course("MIS 101", "Intro. to info. Systems", 140),
                new Course("MIS 301", "System Analysis", 35),
                new Course("MIS 441", "Database Management", 12),
                new Course("CS 155", "Programming in C++", 90),
                new Course("MIS 451", "Web-Based System", 30),
                new Course("MIS 551", "Advanced Web", 30),
                new Course("MIS 651", "Advanced Java", 30)
        };

        //Retrieve the current index form bundle object
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        //Get the course_text_view
        course_text_view = findViewById(R.id.course_text_view);
        //get the view of course_total_fees_text_view
        course_total_fees_text_view = findViewById(R.id.course_total_fees_text_view);
        course_text_view.setText("Course:\n" + allCourses[currentIndex].getCourse_no() + "\n" + allCourses[currentIndex].getCourse_name());


        Button course_total_fees_button = findViewById(R.id.course_total_fees_button);
        course_total_fees_button.setOnClickListener(v -> {
            course_total_fees_text_view.setText("Total Course Fees is:\n" + allCourses[currentIndex].calculateTotalFees() + "$");
            Toast.makeText(MainActivity.this, "Total Course Fees is:\n" + allCourses[currentIndex].calculateTotalFees() + "$", Toast.LENGTH_SHORT).show();
        });
        Button course_next_button = findViewById(R.id.course_next_button);
        course_next_button.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % allCourses.length;
            course_text_view.setText("Course:\n" + allCourses[currentIndex].getCourse_no() + "\n" + allCourses[currentIndex].getCourse_name());
            course_total_fees_text_view.setText("");
        });

        Button courseDetailButton = findViewById(R.id.course_detail_button);
        courseDetailButton.setOnClickListener(v -> {
            //Start a new activity which is CourseActivity
//            Intent intent = new Intent(MainActivity.this, CourseActivity.class);
//            intent.putExtra(EXTRA_COURSE_NO, allCourses[currentIndex].getCourse_no());
//
//            startActivity(intent);

            Intent intent = CourseActivity.newIntent(MainActivity.this,
                    allCourses[currentIndex].getCourse_no(),
                    allCourses[currentIndex].getCourse_name(),
                    allCourses[currentIndex].getMax_enrl(),
                    Course.credits);
            //Only used when sending data from parent MainActivity without expecting result from child activity

            //startActivity(intent);

            //Used when sending data from parent MainActivity and expecting result from child activity
            startActivityIntent.launch(intent);

        });
    }

    ActivityResultLauncher<Intent> startActivityIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() != Activity.RESULT_OK) {
                        return;
                    } else {
                        assert result.getData() != null;
                        Course courseUpdated = CourseActivity.sendMsgCOurseUpdated(result.getData());
                        course_text_view.setText("Course Updated:\n" + courseUpdated.getCourse_no() + "\n" + courseUpdated.getCourse_name());
                        course_total_fees_text_view.setText("Updated total Course Fees is:\n" + courseUpdated.calculateTotalFees() + "$");
                        //Update array component related to current Index
                        allCourses[currentIndex].setCourse_no(courseUpdated.getCourse_no());
                        allCourses[currentIndex].setCourse_name(courseUpdated.getCourse_name());
                        allCourses[currentIndex].setMax_enrl(courseUpdated.getMax_enrl());
                        allCourses[currentIndex].credits = courseUpdated.credits;

                        Toast.makeText(MainActivity.this, "Course Updated info is: " + courseUpdated.getCourse_no() + "\nUpdated Course Fee is: " + courseUpdated.calculateTotalFees() + "$",
                                Toast.LENGTH_SHORT).show();

                    }
                }
            }
    );

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "On Start() method called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause() method called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume() method called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On Stop() method called!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy() method called!");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "Stored current index: " + currentIndex);
        saveInstanceState.putInt(KEY_INDEX, currentIndex);

    }
}