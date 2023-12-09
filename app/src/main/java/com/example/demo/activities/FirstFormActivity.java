package com.example.demo.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.R;
import com.example.demo.database.Student;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class FirstFormActivity extends AppCompatActivity {

    TextInputLayout mWrapperFName, mWrapperLName, mWrapperAddress, mWrapperMobile, mWrapperEmail;
    RadioButton mRbMale, mRbFemale;
    TextView mTvDobValue, mTvDOB;
    Button mBtnNext;

    String dob, age;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_form);

        mWrapperFName = findViewById(R.id.wrapperFname);
        mWrapperLName = findViewById(R.id.wrapperLname);
        mRbMale = findViewById(R.id.rbMale);
        mRbFemale = findViewById(R.id.rbFemale);
        mTvDobValue = findViewById(R.id.tvDOBValue);
        mTvDOB = findViewById(R.id.tvDOB);
        mWrapperAddress = findViewById(R.id.wrapperAddress);
        mWrapperMobile = findViewById(R.id.wrapperMobileNumber);
        mWrapperEmail = findViewById(R.id.wrapperEmail);
        mBtnNext = findViewById(R.id.btnNext);
        EditText etMyText = new EditText(this);
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etMyText.setError("Error");
            }
        });

        setClickListeners();
        validateFields();
    }

    private void validateFields() {
        Objects.requireNonNull(mWrapperEmail.getEditText()).setOnEditorActionListener((v, actionId, event) -> {
            String email = mWrapperEmail.getEditText().getText().toString();
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    mWrapperEmail.setError("Please enter valid email");
                } else {
                    mWrapperEmail.setError("");
                }
                return true;
            }
            return false;
        });
        mWrapperEmail.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrapperEmail.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(mWrapperFName.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrapperFName.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(mWrapperLName.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrapperLName.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(mWrapperAddress.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrapperAddress.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        Objects.requireNonNull(mWrapperMobile.getEditText()).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mWrapperMobile.setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setClickListeners() {
        final Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18);

        // on below line we are getting
        // our day, month and year.
        AtomicInteger year = new AtomicInteger(c.get(Calendar.YEAR));
        AtomicInteger month = new AtomicInteger(c.get(Calendar.MONTH));
        AtomicInteger day = new AtomicInteger(c.get(Calendar.DAY_OF_MONTH));
        mTvDOB.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    // on below line we are passing context.
                    FirstFormActivity.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        age = "" + (c.get(Calendar.YEAR) - year1 + 18);
                        year.set(year1);
                        month.set(monthOfYear);
                        day.set(dayOfMonth);
                        dob = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                        mTvDobValue.setText(dob + " Age: " + age);
                    },
                    // on below line we are passing year,
                    // month and day for selected date in our date picker.
                    year.get(), month.get(), day.get());
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,1980);
            cal.set(Calendar.MONTH, 6);
            cal.set(Calendar.DAY_OF_MONTH, 20);
            datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());

            datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());

            // at last we are calling show to
            // display our date picker dialog.
            datePickerDialog.show();
        });

        mBtnNext.setOnClickListener(v -> {
            if (Objects.requireNonNull(mWrapperFName.getEditText()).getText().toString().isEmpty()) {
                mWrapperFName.setError("Please enter first name");
            } else if (Objects.requireNonNull(mWrapperLName.getEditText()).getText().toString().isEmpty()) {
                mWrapperLName.setError("Please enter last name");
            } else if (Objects.requireNonNull(mWrapperAddress.getEditText()).getText().toString().isEmpty()) {
                mWrapperAddress.setError("Please enter address");
            } else if (Objects.requireNonNull(mWrapperMobile.getEditText()).getText().toString().isEmpty()) {
                mWrapperMobile.setError("Please enter mobile number");
            } else if (Objects.requireNonNull(mWrapperEmail.getEditText()).getText().toString().isEmpty()) {
                mWrapperEmail.setError("Please enter email id");
            } else if (!mRbMale.isChecked() && !mRbFemale.isChecked()) {
                showToast("Please select gender");
            } else if (mTvDobValue.getText().toString().isEmpty()) {
                showToast("Please select Date of birth");
            } else {
                Student student = new Student();
                student.setFirstName(mWrapperFName.getEditText().getText().toString());
                student.setLastName(mWrapperLName.getEditText().getText().toString());
                String gender = mRbMale.isChecked() ? mRbMale.getText().toString() : mRbFemale.getText().toString();
                student.setGender(gender);
                student.setDob(dob);
                student.setAge(age);
                student.setAddress(mWrapperAddress.getEditText().getText().toString());
                student.setMobileNo(mWrapperMobile.getEditText().getText().toString());
                student.setEmailId(mWrapperEmail.getEditText().getText().toString());
                Intent intent = new Intent(FirstFormActivity.this, SecondFormActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(FirstFormActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
