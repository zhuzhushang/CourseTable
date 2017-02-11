package com.example.administrator.coursetable.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.coursetable.R;
import com.example.administrator.coursetable.abstracts.OnActionDown;
import com.example.administrator.coursetable.utils.ScreenUtils;

import static com.example.administrator.coursetable.constants.Constants.EXIT_STYLE_DELETE;
import static com.example.administrator.coursetable.constants.Constants.EXIT_STYLE_EXIT;

/**
 * Created by ShaoQuanwei on 2017/2/10.
 *
 * 删除和退出共用dialog
 *
 */

public class ExitStyleDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private OnActionDown onActionDown;
    private int deleteId;
    private int actionType;
    private TextView type_name,cancel,confirm;
    private View view;




    public ExitStyleDialog(Context context, OnActionDown onActionDown,int actionType) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
        this.onActionDown = onActionDown;
        this.actionType = actionType;

        viewInit();
        dataInit();
        eventInit();
        dialogInit();

    }

    public ExitStyleDialog(Context context, OnActionDown onActionDown,int actionType,int deleteId) {
        super(context, R.style.MyDialogStyle);
        this.context = context;
        this.onActionDown = onActionDown;
        this.actionType = actionType;
        this.deleteId = deleteId;

        viewInit();
        dataInit();
        eventInit();
        dialogInit();


    }

    private void viewInit() {

        view = LayoutInflater.from(context).inflate(R.layout.dialog_exit_style,null);

        type_name = (TextView) view.findViewById(R.id.type_name);
        cancel = (TextView) view.findViewById(R.id.cancel);
        confirm = (TextView) view.findViewById(R.id.confirm);

    }

    private void dataInit() {

        switch (actionType)
        {
            case EXIT_STYLE_DELETE:
                type_name.setText("确定删除");
                break;
            case EXIT_STYLE_EXIT:
                type_name.setText("确定退出");
                break;

        }

    }

    private void eventInit() {

        cancel.setOnClickListener(this);

    }

    private void dialogInit() {

        setContentView(view);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = (int) (ScreenUtils.getScreenWidth(context) * 0.75);
        params.height = ScreenUtils.getScreenHeight(context);
        params.gravity = Gravity.CENTER;

        getWindow().setAttributes(params);
        setCanceledOnTouchOutside(true);
    }


    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.cancel)
        {
            dismiss();
        }else if(v.getId() == R.id.confirm)
        {
            switch (actionType)
            {
                case EXIT_STYLE_EXIT:

                    onActionDown.onExit();

                    break;
                case EXIT_STYLE_DELETE:

                    onActionDown.onDelete(deleteId);

                    break;
            }

            dismiss();
        }

    }
}
