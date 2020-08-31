package com.dkoptin.loftcoin.ui.wallets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dkoptin.loftcoin.R;
import com.dkoptin.loftcoin.databinding.LiWalletBinding;

import org.jetbrains.annotations.NotNull;

public class WalletsAdapter extends RecyclerView.Adapter<WalletsAdapter.ViewHolder> {

    private static final int [] WALLETS = {R.layout.li_wallet,R.layout.li_wallet,R.layout.li_wallet};

    private LayoutInflater inflater;

    @NonNull
    @Override
    public WalletsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiWalletBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WalletsAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return WALLETS.length;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NotNull LiWalletBinding binding) {
            super(binding.getRoot());
        }
    }
}
