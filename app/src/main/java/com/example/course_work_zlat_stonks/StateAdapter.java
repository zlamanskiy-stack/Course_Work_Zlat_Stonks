package com.example.course_work_zlat_stonks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<Metric> metrics;
    public static class Metric {
        private final String name;
        private final String value;
        public Metric(String name, String value) {
            this.name = name;
            this.value = value;
        }
        public String getName() { return name; }
        public String getValue() { return value; }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        final TextView capitalView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.mult_name);
            capitalView = view.findViewById(R.id.mult_rate);
        }
    }

    public StateAdapter(Context context, List<Metric> metrics) {
        this.metrics = metrics;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.resyc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Metric metric = metrics.get(position);
        holder.nameView.setText(metric.getName());
        holder.capitalView.setText(metric.getValue());
    }

    @Override
    public int getItemCount() {
        return metrics.size();
    }
}