package com.example.demo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;

public class StudentItemVieHolder extends RecyclerView.ViewHolder {

    public TextView tvName, tvGender, tvDobAge, tvAddress, tvMobile, tvEmail, tvCourseSubject;
    public ImageView ivCard;


    public StudentItemVieHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvGender = itemView.findViewById(R.id.tvGender);
        tvDobAge = itemView.findViewById(R.id.tvDobAge);
        tvAddress = itemView.findViewById(R.id.tvAddress);
        tvMobile = itemView.findViewById(R.id.tvMobile);
        tvEmail = itemView.findViewById(R.id.tvEmail);
        tvCourseSubject = itemView.findViewById(R.id.tvCourseSubject);
        ivCard = itemView.findViewById(R.id.ivCard);
    }
}