package com.example.exampreparation;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.viewHolder> {

    private List<CategoryModel> categoryModelList;

    public CategoryAdapter( List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startAnother();
//
//
//            }
//        });

         holder.setData(categoryModelList.get(position).getName(),categoryModelList.get(position).getSets());
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    class viewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView title;


        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cardView=itemView.findViewById(R.id.card_item);

            title=itemView.findViewById(R.id.c);
        }
        private void setData(final String title, final int sets){

            this.title.setText(title);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent setIntent=new Intent(cardView.getContext(),SetsActivity.class);
                    setIntent.putExtra("title",title);
                    setIntent.putExtra("sets",sets);
                    cardView.getContext().startActivity(setIntent);
                }
            });
        }
    }
}
