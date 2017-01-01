package com.example.administrator.coursetable.fragment.translate;
import android.support.v7.app.AppCompatActivity;
public class translateActivity extends AppCompatActivity {
//    private EditText editText;
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        initView();
//    }
//
//    private void initView() {
//        setContentView(R.layout.translate);
//        editText = (EditText) findViewById(R.id.et_input);
//        textView = (TextView) findViewById(R.id.tv_main);
//
//        // 利用键盘的按钮搜索
//        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                if (i == EditorInfo.IME_ACTION_SEARCH) {
//                    // 先隐藏键盘
//                    ((InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
//                            .hideSoftInputFromWindow(translateActivity.this.getCurrentFocus().getWindowToken(),
//                                    InputMethodManager.HIDE_NOT_ALWAYS);
//                    httpData();
//                    return true;
//                }
//                return false;
//            }
//        });
//    }
//
//    private void httpData() {
//        OkHttpUtils.get()
//                .url("http://fanyi.youdao.com/openapi.do?")
//                .addParams("keyfrom", "UseYouDaoDemo")
//                .addParams("key", "829332419")
//                .addParams("type", "data")
//                .addParams("doctype", "json")
//                .addParams("version", "1.1")
//                .addParams("q", editText.getText().toString().trim())
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Toast.makeText(translateActivity.this, "请检查网络", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        GsonUtil gsonutil = new GsonUtil();
//                        TranslateBean bean = gsonutil.getJsonObject(response, TranslateBean.class);
//                        if (null != bean) {
//                            int errorCode = bean.getErrorCode();
//                            if (errorCode == 20) {
//                                Toast.makeText(MainActivity.this, "要翻译的文本过长", Toast.LENGTH_SHORT).show();
//                            } else if (errorCode == 40) {
//                                Toast.makeText(MainActivity.this, "不支持该语言", Toast.LENGTH_SHORT).show();
//                            } else if (errorCode == 0) {
//                                textView.setText("");
//                                textView.setText(bean.getTranslation().get(0));
//                            }
//                        }
//                    }
//                });
//    }
}