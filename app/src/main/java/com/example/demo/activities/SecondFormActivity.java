package com.example.demo.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.demo.R;
import com.example.demo.database.DBManager;
import com.example.demo.database.Student;

public class SecondFormActivity extends AppCompatActivity {

  String courseName;
  AutoCompleteTextView mActvSelectedSubject;
  ImageView mIvIdCard;
  Button mBtnSubmit;
  Student student;
  Uri selectedImageUri;
  Spinner spSubject;

  ActivityResultLauncher<Intent> selectIDCardResultLauncher = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> {
      if (result.getResultCode() == Activity.RESULT_OK) {
        // Here, no request code
        Intent data = result.getData();
        assert data != null;
        selectedImageUri = data.getData();
        mIvIdCard.setImageURI(selectedImageUri);
      }
    }
  );

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second_form);

    courseName = getString(R.string.course_name);
    mActvSelectedSubject = findViewById(R.id.actvSelectedSubject);
    mIvIdCard = findViewById(R.id.ivIdCard);
    mBtnSubmit = findViewById(R.id.btnSubmit);
    spSubject = findViewById(R.id.spinnerSubject);

    setSubjectDropdown();
    getStudentDataFromIntent();
    setClickListeners();
  }

  private void setSubjectDropdown() {
    String[] subjects = getResources().getStringArray(R.array.subjects);
    ArrayAdapter adapter = new ArrayAdapter(
      this,
      R.layout.dropdown_item,
      subjects
    );
    mActvSelectedSubject.setAdapter(adapter);
  }

  private void setClickListeners() {
    mIvIdCard.setOnClickListener(v -> {
      imageChooser();
    });
    mBtnSubmit.setOnClickListener(v -> {
      if (selectedImageUri == null) {
        showToast("Please select ID card image");
      } else if (mActvSelectedSubject.getText().toString().isEmpty()) {
        showToast("Please select subject");
      } else {
        student.setCourse(courseName);
        student.setSubject(mActvSelectedSubject.getText().toString());
        student.setIdCard(selectedImageUri.toString());

        DBManager dbManager = new DBManager(SecondFormActivity.this);
        dbManager.openDB();
        long id = dbManager.insert(student);

        if (id != -1) {
          dbManager.closeDB();
          showToast("Record saved!");
          Intent intent = new Intent(
            SecondFormActivity.this,
            DisplayActivity.class
          );
          intent.setFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
          );
          startActivity(intent);
        }
      }
    });

    spSubject.setOnItemSelectedListener(
      new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(
          AdapterView<?> parent,
          View view,
          int position,
          long id
        ) {
          String subject = parent.getAdapter().getItem(position).toString();
          showToast(subject);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
      }
    );
  }

  private void imageChooser() {
    Intent i = new Intent();
    i.setType("image/*");
    i.setAction(Intent.ACTION_GET_CONTENT);
    selectIDCardResultLauncher.launch(i);
  }

  private void getStudentDataFromIntent() {
    student = (Student) getIntent().getSerializableExtra("student");
    assert student != null;
  }

  private void showToast(String message) {
    Toast.makeText(SecondFormActivity.this, message, Toast.LENGTH_LONG).show();
  }
}
