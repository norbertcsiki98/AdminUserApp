package com.example.userapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VoteFragment extends Fragment {
    private RecyclerView recyclerView;
    private com.example.userapp.GridAdapter gridAdapter;
    private ArrayList<String> gridDataList = new ArrayList<>();
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceTasks,mReferenceTasks2;
    private List<String> tasks=new ArrayList<>();
    private List<String> tasks2=new ArrayList<>();
    public static final String SHARED_PREFS="sharedPrefs";

    private Context context;

    @Override
    public void onCreate (Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = getActivity();
        mDatabase=FirebaseDatabase.getInstance();
        mReferenceTasks=mDatabase.getReference("task");
        mReferenceTasks2=mDatabase.getReference("vote");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_vote,container,false);


        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        String name=sharedPreferences.getString("username","");
        String code=sharedPreferences.getString("groupcode","");


        readTasks(code,name);

        recyclerView = v.findViewById(R.id.recyclerView_grid);
        gridAdapter = new com.example.userapp.GridAdapter(gridDataList, context);
        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(gridAdapter);
        GridDataPrepare();
        return v;
    }
    public void readTasks(final String code1,final String name1){

        mReferenceTasks.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tasks.clear();
                List<String> keys=new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Fire_Task task = keyNode.getValue(Fire_Task.class);
                    if(task.getGROUP().equals(code1)==true && task.getSTATUS().equals("activ")==true) {
                        tasks.add(task.getTASK());

                    }
                }
                mReferenceTasks2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        tasks2.clear();
                        List<String> keys=new ArrayList<>();
                        for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                            keys.add(keyNode.getKey());
                            Fire_Vote vote = keyNode.getValue(Fire_Vote.class);
                            if(vote.getGROUP().equals(code1)==true && vote.getNAME().equals(name1)==true) {
                                tasks2.add(vote.getTASK());

                            }
                        }
                        tasks.removeAll(tasks2);
                        TextView tv_task2 = (TextView) getActivity().findViewById(R.id.tv_task);
                        if(tasks.isEmpty()==true){
                            tv_task2.setText("No new task!");
                        }
                        else {
                            String text = tasks.get(0);
                            tv_task2.setText(text);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void GridDataPrepare() {

        gridDataList.add("1");
        gridDataList.add("2");
        gridDataList.add("3");
        gridDataList.add("4");
        gridDataList.add("5");
        gridDataList.add("6");
        gridDataList.add("7");
        gridDataList.add("8");
        gridDataList.add("9");

    }

}
