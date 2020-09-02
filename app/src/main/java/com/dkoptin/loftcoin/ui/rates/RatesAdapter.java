package com.dkoptin.loftcoin.ui.rates;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dkoptin.loftcoin.data.Coin;
import com.dkoptin.loftcoin.databinding.LiRateBinding;

import java.util.List;

public class RatesAdapter extends RecyclerView.Adapter<RatesAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private final List<? extends Coin> coins;

    public RatesAdapter(List<? extends Coin> coins) {
        setHasStableIds(true);
        this.coins = coins;
    }

    @Override
    public long getItemId(int position) {
        return coins.get(position).id();
    }

    @NonNull
    @Override
    public RatesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiRateBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RatesAdapter.ViewHolder holder, int position) {
        holder.bind(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private LiRateBinding binding;

        public ViewHolder(@NonNull LiRateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Coin coin) {
            binding.symbol.setText(coin.symbol());

        }
    }
}
