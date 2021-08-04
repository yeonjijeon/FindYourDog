package com.example.projectyb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    private EditText edt_login_id, edt_login_pw;
    private Button btn_login;
    private  RequestQueue queue;
    private  StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_login_id = findViewById(R.id.edt_login_id);
        edt_login_pw = findViewById(R.id.edt_login_pw);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }

        });


    }
    public void sendRequest(){
        // Voolley Lib 새료운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        String url = "http://211.227.224.206:8081/YB_Project/Login";
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Log.v("resultValue",response);

                    String id = jsonObject.getString("id");
                    String pw = jsonObject.getString("pw");
                    String name = jsonObject.getString("name");
                    int membercode = jsonObject.getInt("membercode");
                    int tel = jsonObject.getInt("tel");
                    String address = jsonObject.getString("address");

                    MemberDTO dto = new MemberDTO(id,pw,name,membercode,tel,address);


                    Log.v("resultValue", String.valueOf(dto));
                    if (id.equals(edt_login_id.getText().toString())){

                        PreferenceManager.setString(getApplicationContext(),"id",dto.getId());
                        PreferenceManager.setString(getApplicationContext(),"pw",dto.getPw());
                        PreferenceManager.setString(getApplicationContext(),"name",dto.getName());
                        PreferenceManager.setInt(getApplicationContext(),"membercode",dto.getMembercode());
                        PreferenceManager.setInt(getApplicationContext(),"tel",dto.getTel());
                        PreferenceManager.setString(getApplicationContext(),"address",dto.getAddress());

                        Intent intent = new Intent(getApplicationContext(),Side_login.class);
                        startActivity(intent);

                        Log.v("resultValue","로그인성공");
                    }else{
                        Toast.makeText(getApplicationContext(),"로그인 실패",Toast.LENGTH_SHORT).show();
                        Log.v("resultValue","로그인실패");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
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


                params.put("login_id", edt_login_id.getText().toString());
                params.put("login_pw", edt_login_pw.getText().toString());

                Log.v("login_id",edt_login_id.getText().toString());
                Log.v("login_pw",edt_login_pw.getText().toString());







                return params;
            }
        };

        queue.add(stringRequest);
    }
}