package com.example.navi_ga.ui.statistical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.navi_ga.databinding.FragmentSettingBinding;
import com.example.navi_ga.databinding.FragmentStatisticalBinding;
import com.example.navi_ga.ui.setting.SettingViewModel;


public class StatisticalFragment extends Fragment {

    private FragmentStatisticalBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatisticalViewModel statisticalViewModel =
                new ViewModelProvider(this).get(StatisticalViewModel.class);

        binding = FragmentStatisticalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textStatistical;
        statisticalViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}