package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CourseActivity extends AppCompatActivity {
    private Button courseUpdateButton;
    private EditText courseIdEditText;
    private EditText courseNameEditText;
    private EditText courseMaxiEnrollEditText;
    private EditText courseCreditEditText;

    private static final String EXTRA_COURSE_NO = "com.example.courseproject.course_no";
    private static final String EXTRA_COURSE_NAME = "com.example.courseproject.course_name";
    private static final String EXTRA_COURSE_MAX_ENROLL = "com.example.courseproject.course_max_enroll";
    private static final String EXTRA_COURSE_CREDIT = "com.example.courseproject.course_credit";

    private String courseIdRetrieved;

    public static Intent newIntent(Context packageContext, String courseId, String courseName, int maxEnroll, int credit){
        Intent intent = new Intent(packageContext, CourseActivity.class);
        intent.putExtra(EXTRA_COURSE_NO, courseId);
        intent.putExtra(EXTRA_COURSE_NAME, courseName);
        intent.putExtra(EXTRA_COURSE_MAX_ENROLL, maxEnroll);
        intent.putExtra(EXTRA_COURSE_CREDIT, credit);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        courseIdRetrieved = getIntent().getStringExtra(EXTRA_COURSE_NO);


                //get the view of courseIdEditText
        courseIdEditText = findViewById(R.id.course_no_edit_text);
        courseIdEditText.setText(courseIdRetrieved);
        courseNameEditText = findViewById(R.id.course_name_edit_text);
        courseNameEditText.setText(getIntent().getStringExtra(EXTRA_COURSE_NAME));
        courseMaxiEnrollEditText = findViewById(R.id.course_max_enrl_edit_text);
        courseMaxiEnrollEditText.setText(String.valueOf(getIntent().getIntExtra(EXTRA_COURSE_MAX_ENROLL, 0)));
        courseCreditEditText = findViewById(R.id.course_credit_edit_text);
        courseCreditEditText.setText(String.valueOf(getIntent().getIntExtra(EXTRA_COURSE_CREDIT, 0)));

        courseUpdateButton = findViewById(R.id.course_update_button);
        courseUpdateButton.setOnClickListener(v -> {
            //Send update course info result to the parent activity, MainActivity
            setCourseUpdateResult(courseIdEditText.getText().toString(),
                                courseNameEditText.getText().toString(),
                                Integer.parseInt(courseMaxiEnrollEditText.getText().toString()),
                                Integer.parseInt(courseCreditEditText.getText().toString()));
        });
    }

    private void setCourseUpdateResult(String courseId, String courseName, int courseMaxEnroll, int courseCredit){
        Intent dataIntent = new Intent();
        dataIntent.putExtra(EXTRA_COURSE_NO, courseId);
        dataIntent.putExtra(EXTRA_COURSE_NAME, courseName);
        dataIntent.putExtra(EXTRA_COURSE_MAX_ENROLL, courseMaxEnroll);
        dataIntent.putExtra(EXTRA_COURSE_CREDIT, courseCredit);
        setResult(RESULT_OK, dataIntent);
    }

    public static Course sendMsgCOurseUpdated(Intent resultIntent){
        Course courseUpdated = new Course();
        courseUpdated.setCourse_no(resultIntent.getStringExtra(EXTRA_COURSE_NO));
        courseUpdated.setCourse_name(resultIntent.getStringExtra(EXTRA_COURSE_NAME));
        courseUpdated.setMax_enrl(resultIntent.getIntExtra(EXTRA_COURSE_MAX_ENROLL, 0));
        courseUpdated.credits = resultIntent.getIntExtra(EXTRA_COURSE_CREDIT, 0);

        return courseUpdated;
    }
}