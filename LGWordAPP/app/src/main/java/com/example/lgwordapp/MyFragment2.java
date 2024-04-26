package com.example.lgwordapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lgwordapp.adapter.HistoryListAdapter;
import com.example.lgwordapp.db.HistoryDbHelper;
import com.example.lgwordapp.info.HistoryInfo;

import java.util.List;


public class MyFragment2 extends Fragment {
                private static final String TAG = "MyFragment2";
//                private RecyclerView recyclerView;
//                private HistoryListAdapter historyListAdapter;
//                private List<HistoryInfo> mList = new ArrayList<>();
                private View rootview;

//                private EditText mEditText;
//                private ImageView mImageView;
//                private ListView mListView;
//                private TextView mTextView;
//                Context context;
//                Cursor cursor;

                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    rootview = inflater.inflate(R.layout.search_history, container, false);

                        // 初始化控件
                        EditText editText = rootview.findViewById(R.id.edittext);
                        TextView textView = rootview.findViewById(R.id.textview);
                        RecyclerView recyclerView = rootview.findViewById(R.id.recyclerview);

                        // 初始化适配器
                        HistoryListAdapter historyListAdapter = new HistoryListAdapter(requireActivity());
                        // 设置适配器
                        recyclerView.setAdapter(historyListAdapter);

                    //从数据库获取数据
                    List<HistoryInfo> historyInfoList = HistoryDbHelper.getInstance(requireActivity()).queryHistoryListData(null);

                    // 设置数据到适配器
                    historyListAdapter.setListData(historyInfoList);


                    //模糊搜索
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String keyword = editText.getText().toString();
                            List<HistoryInfo> searchResults = HistoryDbHelper.getInstance(requireActivity()).searchHistoryListData(keyword);
                            historyListAdapter.setListData(searchResults);
                        }
                    });

                        return rootview;
                    }



        @Override
        public void onDestroyView() {
            super.onDestroyView();
            Log.i(TAG, "onDestroyView: 0000-------MyFragment2");
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            Log.i(TAG, "onDestroy: 0000--------MyFragment2");
        }


        public MyFragment2() {
        }

    }

