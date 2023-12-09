package com.example.demo.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.adapter.OnItemClickListener;
import com.example.demo.adapter.StudentListAdapter;
import com.example.demo.database.DBManager;
import com.example.demo.database.Student;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        DBManager dbManager = new DBManager(this);
        dbManager.openDB();
        ArrayList<Student> studentsData = dbManager.fetchAll();

        StudentListAdapter adapter = new StudentListAdapter();
        adapter.updateData(studentsData);
        adapter.OnItemCLick(new OnItemClickListener() {
            @Override
            public void studentItemClickListener(Student student) {
                new AlertDialog.Builder(DisplayActivity.this)
                        .setTitle(student.getFirstName())
                        .setMessage("Are you sure, you want to delete student?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                dbManager.deleteStudent(student.getId());
                                adapter.updateData(dbManager.fetchAll());
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });
//        adapter.setContentResolver(getContentResolver());

        RecyclerView rvStudentList = findViewById(R.id.rvStudentList);
        rvStudentList.setAdapter(adapter);
        findViewById(R.id.btnAddStudent).setOnClickListener(v -> {
            startActivity(new Intent(DisplayActivity.this, FirstFormActivity.class));
            finish();
        });
    }
}