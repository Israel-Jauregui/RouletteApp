package com.zybooks.roulette;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class RedBlackDialogFragment extends DialogFragment {

    public interface OnRedBlackSelectedListener {
        void onRedBlackClick(int which);
    }

    private OnRedBlackSelectedListener mListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle(R.string.redBlackPick);
        builder.setItems(R.array.redBlack_array, (dialog, which) -> mListener.onRedBlackClick(which));
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnRedBlackSelectedListener) context;
    }
}

