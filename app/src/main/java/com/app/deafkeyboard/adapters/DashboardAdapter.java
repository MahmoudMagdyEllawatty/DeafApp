package com.app.deafkeyboard.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MediatorLiveData;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.deafkeyboard.LoginActivity;
import com.app.deafkeyboard.R;
import com.app.deafkeyboard.activities.admin.AdminDashboardActivity;
import com.app.deafkeyboard.activities.admin.PostersActivity;
import com.app.deafkeyboard.activities.user.LearningActivity;
import com.app.deafkeyboard.activities.user.SelectLearningActivity;
import com.app.deafkeyboard.activities.user.UserChatsActivity;
import com.app.deafkeyboard.activities.user.UserPostersActivity;
import com.app.deafkeyboard.utils.DashboardItem;
import com.app.deafkeyboard.utils.SharedData;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private ArrayList<DashboardItem> dashboardItems;
    private Context context;

    public DashboardAdapter(ArrayList<DashboardItem> dashboardItems, Context context) {
        this.dashboardItems = dashboardItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.dashboard_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.englishText.setText(dashboardItems.get(position).getEnglish());
        holder.arabicText.setText(dashboardItems.get(position).getArabic());


        holder.englishSigns.setAdapter(new ItemAdapter(dashboardItems.get(position).getEnglishSigns(),context));
        holder.arabicSigns.setAdapter(new ItemAdapter(dashboardItems.get(position).getArabicSigns(),context));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dashboardItems.get(position).getEnglish().equals("Posters")){
                    Intent intent = new Intent(context, PostersActivity.class);
                    context.startActivity(intent);
                }else if(dashboardItems.get(position).getEnglish().equals("posters")){
                    Intent intent = new Intent(context, UserPostersActivity.class);
                    context.startActivity(intent);
                }else if(dashboardItems.get(position).getEnglish().equals("Learning")){
                    Intent intent = new Intent(context, SelectLearningActivity.class);
                    context.startActivity(intent);
                }else if(dashboardItems.get(position).getEnglish().equals("Chat")){
                    Intent intent = new Intent(context, UserChatsActivity.class);
                    context.startActivity(intent);
                }else if(dashboardItems.get(position).getEnglish().equals("Logout")){
                    SharedData.loggedUser = null;
                        SharedData.chat = null;
                        SharedData.mCurrentIndex = new MediatorLiveData<>();

                        Intent intent = new Intent(context, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{

        private TextView englishText;
        private TextView arabicText;

        private RecyclerView englishSigns;
        private RecyclerView arabicSigns;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            englishText = itemView.findViewById(R.id.english_text);
            arabicText = itemView.findViewById(R.id.arabic_text);

            englishSigns = itemView.findViewById(R.id.english_signs);
            arabicSigns = itemView.findViewById(R.id.arabic_signs);


            englishSigns.setItemAnimator(new DefaultItemAnimator());
            arabicSigns.setItemAnimator(new DefaultItemAnimator());

            englishSigns.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
            arabicSigns.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
        }
    }
}
