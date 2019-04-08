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

public class FragmentRead extends Fragment {

    private TextView textView;
    private Button button;
    private ReadCallback readCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            readCallback = (ReadCallback) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_read, container);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView);
        button = view.findViewById(R.id.btn_read);
        button.setOnClickListener(v -> textView.setText(readCallback.read()));

    }

    public interface ReadCallback {
        String read();
    }
}
