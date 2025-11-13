package com.zybooks.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class RangeDialogFragment extends DialogFragment {

    public interface OnRangeSelectedListener {
        void onRangeClick(int which);
    }

    private OnRangeSelectedListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnRangeSelectedListener) {
            listener = (OnRangeSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString() +
                    " must implement OnRangeSelectedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.rangePick)
                .setItems(R.array.range_array, (dialog, which) -> {
                    if (listener != null) {
                        listener.onRangeClick(which);
                    }
                });
        return builder.create();
    }
}

