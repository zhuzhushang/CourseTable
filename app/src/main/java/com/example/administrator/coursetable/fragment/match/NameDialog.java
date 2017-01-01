package com.example.administrator.coursetable.fragment.match;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.example.administrator.coursetable.R;

/**
 * Created by garma on 2017/1/1.
 */

public class NameDialog extends Dialog{
    Context context;



    public NameDialog(Context context) {
        super(context);
        this.context = context;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.text);

    }
}
