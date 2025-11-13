package com.zybooks.roulette;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
public class OddsEvenDialogFragment extends DialogFragment
{
    public interface OnOddsEvenSelectedListener{
    void onOddsEvenClick(int which);

        // Called when user picks a number btw 0-38
        void onNumberSelected(int number);
    }
    private OnOddsEvenSelectedListener mListener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
    AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
    builder.setTitle(R.string.oddsEvensPick);
    builder.setItems(
            R.array.oddsEven_array, (dialog, which) ->
            {
                mListener.onOddsEvenClick(which);

            }
    );
    return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        mListener = (OnOddsEvenSelectedListener) context;


    }
}
