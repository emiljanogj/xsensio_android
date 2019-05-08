package com.xsensio.nfcsensorcomm.mainactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.xsensio.nfcsensorcomm.R;

public class LoadingScreen extends Fragment {

    private OnFragmentInteractionListener mListener;

    public LoadingScreen() {
        // Required empty public constructor
    }

    private DonutProgress progress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loading_screen, container, false);
        progress=view.findViewById(R.id.ls_progress);
        progress.setMax(100);
        progress.setProgress(0);
        final Handler handler1=new Handler();
        final Handler handler2=new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateReadSensorProgress("Receiving data for Sensor 3, Case 2",50);
            }
        },1000);
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateReadSensorProgress("Receiving data for Sensor 3, Case 2",100);
            }
        },2000);
        return view;
    }

    public void updateReadSensorProgress(String taskDescription, int completionRatio) {
        switch (taskDescription) {
            case "Receiving data for Sensor 1, Case 2":
                progress.setProgress(completionRatio/3);
                break;
            case "Receiving data for Sensor 2, Case 2":
                progress.setProgress(100/3+completionRatio/3);
                break;
            case "Receiving data for Sensor 3, Case 2":
                progress.setProgress(200/3+completionRatio/3);
                if(completionRatio==100){
                    ((MainActivity)getActivity()).changeFragment("resultScreen");
                }
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}