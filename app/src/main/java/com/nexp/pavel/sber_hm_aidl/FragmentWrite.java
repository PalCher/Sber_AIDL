package com.nexp.pavel.sber_hm_aidl;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FragmentWrite extends Fragment {

    private EditText editText;
    private Button button;

    WriteCallback activityCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCallbacks = (WriteCallback) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_write, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.btn_write);
        button.setOnClickListener(v -> activityCallbacks.write(editText.getText().toString()));

    }

    public interface WriteCallback {
        void write(String text);
    }

}
