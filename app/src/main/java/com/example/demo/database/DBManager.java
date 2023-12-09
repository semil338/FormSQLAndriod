package com.example.demo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DBManager {

    private final Context context;
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public void openDB() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    public void closeDB() {
        dbHelper.close();
    }


    public long insert(Student student) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(StudentEntry.firstName, student.firstName);
        contentValue.put(StudentEntry.lastName, student.lastName);
        contentValue.put(StudentEntry.gender, student.gender);
        contentValue.put(StudentEntry.dob, student.dob);
        contentValue.put(StudentEntry.age, student.age);
        contentValue.put(StudentEntry.address, student.address);
        contentValue.put(StudentEntry.mobileNo, student.mobileNo);
        contentValue.put(StudentEntry.emailId, student.emailId);
        contentValue.put(StudentEntry.course, student.course);
        contentValue.put(StudentEntry.subject, student.subject);
        contentValue.put(StudentEntry.idCard, student.idCard);
        return database.insert(StudentEntry.TABLE_NAME, null, contentValue);
    }

    public ArrayList<Student> fetchAll() {
        ArrayList<Student> students = new ArrayList<>();
        Cursor c = database.rawQuery("SELECT * FROM " + StudentEntry.TABLE_NAME, null);
        while (c.moveToNext()) {
            Student s = new Student();
            s.setId(c.getLong(0));
            s.setFirstName(c.getString(1));
            s.setLastName(c.getString(2));
            s.setGender(c.getString(3));
            s.setDob(c.getString(4));
            s.setAge(c.getString(5));
            s.setAddress(c.getString(6));
            s.setMobileNo(c.getString(7));
            s.setEmailId(c.getString(8));
            s.setCourse(c.getString(9));
            s.setSubject(c.getString(10));
            s.setIdCard(c.getString(11));
            students.add(s);
        }
        return students;
    }

    public Cursor fetchByFirstName(String firstName) {
        String[] columns = new String[]{StudentEntry._ID, StudentEntry.firstName, StudentEntry.lastName, StudentEntry.gender, StudentEntry.dob, StudentEntry.age, StudentEntry.address, StudentEntry.mobileNo, StudentEntry.emailId, StudentEntry.course, StudentEntry.subject, StudentEntry.idCard};
        String selection = StudentEntry.firstName + " = ?";
        String[] selectionArgs = {firstName};
        Cursor cursor = database.query(StudentEntry.TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateStudent(long _id, Student student) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(StudentEntry.firstName, student.firstName);
        contentValue.put(StudentEntry.lastName, student.lastName);
        contentValue.put(StudentEntry.gender, student.gender);
        contentValue.put(StudentEntry.dob, student.dob);
        contentValue.put(StudentEntry.age, student.age);
        contentValue.put(StudentEntry.address, student.address);
        contentValue.put(StudentEntry.mobileNo, student.mobileNo);
        contentValue.put(StudentEntry.emailId, student.emailId);
        contentValue.put(StudentEntry.course, student.course);
        contentValue.put(StudentEntry.subject, student.subject);
        contentValue.put(StudentEntry.idCard, student.idCard);
        return database.update(StudentEntry.TABLE_NAME, contentValue, StudentEntry._ID + " = " + _id, null);
    }

    public void deleteStudent(long _id) {
        database.delete(StudentEntry.TABLE_NAME, StudentEntry._ID + "=" + _id, null);
    }

}
