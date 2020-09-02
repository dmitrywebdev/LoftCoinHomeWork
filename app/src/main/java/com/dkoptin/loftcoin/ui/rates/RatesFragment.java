package com.dkoptin.loftcoin.ui.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.dkoptin.loftcoin.R;
import com.dkoptin.loftcoin.data.Coin;
import com.dkoptin.loftcoin.databinding.FragmentRatesBinding;

import java.util.List;

public class RatesFragment extends Fragment implements RatesView {

    private FragmentRatesBinding binding;

    private RatesPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RatesPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rates, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRatesBinding.bind(view);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.setHasFixedSize(true);
        presenter.attach(this);
    }

    @Override
    public void onDestroyView() {
        presenter.detach(this);
        super.onDestroyView();
    }

    @Override
    public void showCoins(@NonNull List<? extends Coin> coins) {
        binding.recycler.setAdapter(new RatesAdapter(coins));
    }

    @Override
    public void showError(@NonNull String error) {

    }
}
