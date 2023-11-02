package com.example.notesfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class TitleFragment extends ListFragment {
    boolean mDualPane;
    int mCurrentPos = 0;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,Notes.NOTESTITLE));

        View detailsFrame = getActivity().findViewById(R.id.details);
        mDualPane = detailsFrame!=null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null){
            mCurrentPos = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane){
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            ShowDetails(mCurrentPos);
        }
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ShowDetails(position);
    }

    private void ShowDetails(int index) {
        mCurrentPos = index;
        if (mDualPane){
            getListView().setItemChecked(index, true);
            DetailFragment detail = (DetailFragment)getFragmentManager().findFragmentById(R.id.details);
            if (detail==null || detail.getShownIndex() != index){
                detail = DetailFragment.newInstance(index);

                FragmentTransaction ft = getFragmentManager()
                        .beginTransaction();
                        ft.replace(R.id.details,detail)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
            }

        }else {
            //portrait
            Intent i = new Intent();
            i.setClass(getActivity(),DetailActivity.class);
            i.putExtra("index", index);
            startActivity(i);
        }
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",mCurrentPos);
    }
}
