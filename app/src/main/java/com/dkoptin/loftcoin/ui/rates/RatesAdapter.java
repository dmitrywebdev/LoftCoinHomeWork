package com.dkoptin.loftcoin.ui.rates;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dkoptin.loftcoin.BuildConfig;
import com.dkoptin.loftcoin.R;
import com.dkoptin.loftcoin.data.Coin;
import com.dkoptin.loftcoin.databinding.LiRateBinding;
import com.dkoptin.loftcoin.util.ChangeFormatter;
import com.dkoptin.loftcoin.util.Formatter;
import com.dkoptin.loftcoin.util.ImageLoader;
import com.dkoptin.loftcoin.util.OutlineCircle;
import com.dkoptin.loftcoin.util.PriceFormatter;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import javax.inject.Inject;

public class RatesAdapter extends ListAdapter<Coin, RatesAdapter.ViewHolder> {

    private LayoutInflater inflater;

    private int colorNegative = Color.RED;

    private int colorPositive = Color.GREEN;

    private int colorBackgroundDark = Color.DKGRAY;

    private int colorBackgroundGray = Color.GRAY;

    private PriceFormatter priceFormatter;

    private ChangeFormatter changeFormatter;

    private ImageLoader imageLoader;

    @Inject
    RatesAdapter(PriceFormatter priceFormatter, ChangeFormatter changeFormatter, ImageLoader imageLoader) {
        super(new DiffUtil.ItemCallback<Coin>() {
            @Override
            public boolean areItemsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return oldItem.id() == newItem.id();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return Objects.equals(oldItem, newItem);
            }

            @Nullable
            @Override
            public Object getChangePayload(@NonNull Coin oldItem, @NonNull Coin newItem) {
                return newItem;
            }
        });
        this.priceFormatter = priceFormatter;
        this.changeFormatter = changeFormatter;
        this.imageLoader = imageLoader;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).id();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiRateBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Coin coin = getItem(position);
        holder.binding.symbol.setText(coin.symbol());
        holder.binding.price.setText(priceFormatter.format(coin.currencyCode(), coin.price()));
        holder.binding.change.setText(changeFormatter.format(coin.change24h()));
        if (coin.change24h() > 0) {
            holder.binding.change.setTextColor(colorPositive);
        } else {
            holder.binding.change.setTextColor(colorNegative);
        }

        if (position %2 == 0) {
            holder.binding.getRoot().setBackgroundColor(colorBackgroundGray);
        } else {
            holder.binding.getRoot().setBackgroundColor(colorBackgroundDark);
        }

        imageLoader
                .load(BuildConfig.IMG_ENDPOINT + coin.id() + ".png")
                .into(holder.binding.logo);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            final Coin coin = (Coin) payloads.get(0);
            holder.binding.price.setText(priceFormatter.format(coin.currencyCode(), coin.price()));
            holder.binding.change.setText(changeFormatter.format(coin.change24h()));
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        inflater = LayoutInflater.from(context);
        TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.textNegative, v, true);
        colorNegative = v.data;
        context.getTheme().resolveAttribute(R.attr.textPositive, v, true);
        colorPositive = v.data;

        context.getTheme().resolveAttribute(R.attr.rateBackgroundDark, v, true);
        colorBackgroundDark = v.data;
        context.getTheme().resolveAttribute(R.attr.rateBackgroundGray, v, true);
        colorBackgroundGray = v.data;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final LiRateBinding binding;

        public ViewHolder(@NonNull LiRateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            OutlineCircle.apply(binding.logo);
        }
    }
}
