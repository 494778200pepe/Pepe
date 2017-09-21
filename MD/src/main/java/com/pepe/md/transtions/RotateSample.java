package com.pepe.md.transtions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pepe.md.R;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;

/**
 * Created by Andrey Kulikov on 17/04/16.
 */
public class RotateSample extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_md_rotate, container, false);

        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.transitions_container);
        final View icon = transitionsContainer.findViewById(R.id.icon);

        icon.setOnClickListener(new View.OnClickListener() {

            boolean mRotated;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer, new Rotate());

                mRotated = !mRotated;
                icon.setRotation(mRotated ? 135 : 0);
            }

        });

        return view;
    }

}
