package com.example.demo.adapter;

import android.content.ContentResolver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.database.Student;

import java.util.ArrayList;

public class StudentListAdapter extends RecyclerView.Adapter<StudentItemVieHolder> {
    ArrayList<Student> studentArrayList = new ArrayList<>();
    private ContentResolver contentResolver;
    OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public StudentItemVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item, parent, false);
        return new StudentItemVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentItemVieHolder holder, int position) {
        Student s = studentArrayList.get(position);
        holder.tvName.setText(s.getFirstName() + " " + s.getLastName());
        holder.tvGender.setText(s.getGender());
        holder.tvDobAge.setText("DOB: " + s.getDob() + " Age: " + s.getAge());
        holder.tvAddress.setText(s.getAddress());
        holder.tvMobile.setText(s.getMobileNo());
        holder.tvEmail.setText(s.getEmailId());
        holder.tvCourseSubject.setText("Course: " + s.getCourse() + " Subject: " + s.getSubject());
        holder.ivCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.studentItemClickListener(s);
            }
        });
//        Uri uri = Uri.parse(s.getIdCard());
//        holder.ivCard.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return studentArrayList.size();
    }

    public void updateData(ArrayList<Student> students) {
        studentArrayList.clear();
        studentArrayList.addAll(students);
        notifyDataSetChanged();
    }

    public void setContentResolver(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public void OnItemCLick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
