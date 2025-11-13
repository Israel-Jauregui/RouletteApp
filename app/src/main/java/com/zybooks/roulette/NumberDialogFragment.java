package com.zybooks.roulette;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class NumberDialogFragment extends DialogFragment {

    public interface OnNumberSelectedListener {
        void onNumberClick(int number);
    }

    private OnNumberSelectedListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (OnNumberSelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement NumberDialogFragment.OnNumberSelectedListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.numberPick)
                .setItems(R.array.number_array, (dialog, which) -> {
                    if (mListener != null) mListener.onNumberClick(which);
                })
                .setNegativeButton("Cancel", null);
        return builder.create();
    }
}




