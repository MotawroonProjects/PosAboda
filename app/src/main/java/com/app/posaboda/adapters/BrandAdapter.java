package com.app.posaboda.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.posaboda.R;
import com.app.posaboda.activities_fragments.activity_home.fragments.Fragment_Home;
import com.app.posaboda.databinding.BrandRowBinding;
import com.app.posaboda.databinding.TypeRowBinding;
import com.app.posaboda.models.DeptTypeBrandModel;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.MyHolder> {

    private List<DeptTypeBrandModel> list;
    private Context context;
    private Fragment_Home fragment_home;
    private int i = -1;
    private int old_pos = -2;

    public BrandAdapter(List<DeptTypeBrandModel> list, Context context, Fragment_Home fragment_home) {
        this.list = list;
        this.context = context;
        this.fragment_home = fragment_home;


    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BrandRowBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.brand_row, parent, false);
        return new MyHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        DeptTypeBrandModel brandModel = list.get(position);
        holder.binding.setModel(brandModel);
        if (brandModel.isSelected()){
            holder.binding.image.setBorderColor(ContextCompat.getColor(context,R.color.colorPrimary));
        }else {
            holder.binding.image.setBorderColor(ContextCompat.getColor(context,R.color.gray2));

        }

        holder.itemView.setOnClickListener(view -> {
            if (old_pos!=-2){
                DeptTypeBrandModel model1 = list.get(old_pos);
                if (old_pos!= holder.getAdapterPosition()){
                    model1.setSelected(false);

                }else {
                    if (model1.isSelected()){
                        model1.setSelected(false);

                    }else {
                        model1.setSelected(true);

                    }
                }
                list.set(old_pos,model1);
                notifyItemChanged(old_pos);


            }

            i = holder.getAdapterPosition();
            DeptTypeBrandModel model2 = list.get(i);

            if (i!=old_pos){
                model2.setSelected(true);
                list.set(i,model2);
                notifyItemChanged(i);
                old_pos =i;
            }
            fragment_home.setBrandItemData(model2);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        private BrandRowBinding binding;

        public MyHolder(BrandRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }


    }


}
