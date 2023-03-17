
package com.example.studentgradeproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Student Grade Project";
    private static final String KEY_INDEX = "index";
    private int currentIndex = 0;


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Build up the collection(array of grades)
        Grade[] grades = new Grade[]{
                new Grade(1,"Graham","Bill",69,70,98,80,90 ),
                new Grade(2,"Sanchez","Jim",88,72,90,83,93),
                new Grade(3,"White","Peter",85,80,45,93,70),
                new Grade(4,"Phelp","David",70,60,60,90,70),
                new Grade(5,"Lewis","Sheila",50,76,87,59,72),
                new Grade(6,"James","Thomas",89,99,97,98,99)
        };
        //Retrieve the current index from bundle object if its not null
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        //Get the views of all components will need
        TextView student_text_view = findViewById(R.id.student_text_view);
        TextView grade_text_view = findViewById(R.id.grade_text_view);
        Button next_student_button = findViewById(R.id.next_student_button);
        Button pre_student_button = findViewById(R.id.pre_student_button);
        Button display_button = findViewById(R.id.display_button);

        //Set the student text view for first run
        student_text_view.setText("Student: " + grades[currentIndex].getStudent_Fname() + " " + grades[currentIndex].getStudent_Lname());

        display_button.setOnClickListener(v -> {
            ((TextView)findViewById(R.id.grade_text_view)).setText(String.format("Grade Average is: %.2f%%", grades[currentIndex].Calculate_GradeAverage()));
            Toast.makeText(MainActivity.this, String.format("Grade Average is: %.2f%%", grades[currentIndex].Calculate_GradeAverage()), Toast.LENGTH_SHORT).show();
        });


        next_student_button.setOnClickListener(v -> {
            currentIndex = (currentIndex + 1) % grades.length;
            student_text_view.setText("Student: " + grades[currentIndex].getStudent_Fname() + " " + grades[currentIndex].getStudent_Lname());
            grade_text_view.setText("Grade Average is: ");
        });


        pre_student_button.setOnClickListener(v -> {
            currentIndex = (currentIndex - 1 + grades.length) % grades.length;
            student_text_view.setText("Student: " + grades[currentIndex].getStudent_Fname() + " " + grades[currentIndex].getStudent_Lname());
            grade_text_view.setText("Grade Average is: ");
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, " onStart() method called!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, " onPause() method called!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, " onResume() method called!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, " onStop() method called!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, " onDestroy() method called!");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d("Student Grade Project", "Stored current index: " + currentIndex);
        saveInstanceState.putInt(KEY_INDEX, currentIndex);
    }
}