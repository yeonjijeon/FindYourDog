package com.example.projectyb;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectyb.databinding.ActivityJoinBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Join extends Fragment {

    private EditText edt_join_id, edt_join_pw, edt_join_name, edt_join_tel, edt_join_addr;
    private Button btn_join, btn_id_check;
    private RequestQueue queue;
    private StringRequest stringRequest;
    private CheckBox shelter_check;
    private String result = "";
    private TextView tv_id_check;
    private String idcheck;

    private ActivityJoinBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ActivityJoinBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv_id_check = binding.tvIdCheck;
        edt_join_id = binding.edtJoinId;
        edt_join_pw = binding.edtJoinPw;
        edt_join_name = binding.edtJoinName;
        edt_join_tel = binding.edtJoinTel;
        edt_join_addr = binding.edtJoinAddr;
        btn_join = binding.btnJoin;
        btn_id_check = binding.btnIdCheck;
        shelter_check = binding.shelterCheck;


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(v);
            }
        });
        btn_id_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest(v);
            }
        });
        return root;
    }

    public void sendRequest(View v) {
        // Voolley Lib 새료운 요청객체 생성
        String url = "";
        queue = Volley.newRequestQueue(getContext());
        if (v.equals(btn_join)) {
            url = "http://211.227.224.206:8081/YB_Project/Join";
        } else if (v.equals(btn_id_check)) {
            url = "http://211.227.224.206:8081/YB_Project/Join_id_check";
        }
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                if (v.equals(btn_join)) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        String result = jsonObject.getString("isCheck");
                        Log.v("resultValue", result);
                        if (result.equals("true")) {
                            Intent intent = new Intent(getContext(), Sidebar.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), "회원가입 실패..", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else if (v.equals(btn_id_check)) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        idcheck = jsonObject.getString("isCheck");
                        Log.v("iii", idcheck);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                if (idcheck.equals("true")) {
                    tv_id_check.setText("X");
                } else if (idcheck.equals("false")) {
                    tv_id_check.setText("O");
                } else {
                    tv_id_check.setText("no");
                }


            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                if (v.equals(btn_join)) {
                    if (shelter_check.isChecked()) {
                        result = "1";
                    } else {
                        result = "2";
                    }

                    params.put("join_id", edt_join_id.getText().toString());
                    params.put("join_pw", edt_join_pw.getText().toString());
                    params.put("join_name", edt_join_name.getText().toString());
                    params.put("join_tel", edt_join_tel.getText().toString());
                    params.put("join_addr", edt_join_addr.getText().toString());
                    params.put("join_check", result);


                    Log.v("join_id", edt_join_id.getText().toString());
                    Log.v("join_check", result);


                } else if (v.equals(btn_id_check)) {
                    params.put("join_id", edt_join_id.getText().toString());
                }
                return params;

            }
        };

        queue.add(stringRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}