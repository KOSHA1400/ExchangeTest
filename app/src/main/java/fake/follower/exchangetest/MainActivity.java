package fake.follower.exchangetest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Spinner spinnerStart;
    Spinner spinnerEnd;


    //دکمه ثبت‌
    Button btnSend;

    //فیلد ورودی
    EditText edtInput;

    //نمایش نتیجه
    TextView txtResult;


    //آرایه ای از رشته ها
    String[] listArray = {"دلار", "یورو", "ریال"};


    float price = 0f;
    int startPricePosition = 0;
    int endPricePosition = 0;

    //نتیجه تبدیل واحد
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        spinnerStart =(Spinner)findViewById(R.id.spinner_start);
        spinnerEnd =(Spinner)findViewById(R.id.spinner_end);
        edtInput = findViewById(R.id.edt_price);
        btnSend = findViewById(R.id.btn_send);
        txtResult=findViewById(R.id.txt_result);



       // ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txt_list_item, istArray);

        //افزودن adapter به لیست باکس جهت نمایش آیتم ها
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listArray);
        //ساختن یک فایل xml برای نمایش  لایه آیتم ها
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //تنظیم کردن adapter برای هر لیست
        spinnerStart.setAdapter(arrayAdapter);
        spinnerEnd.setAdapter(arrayAdapter);



        //وقتی روی آیتم های لیست باکس کلیک شد این توابع اجرا می شوند
        spinnerStart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startPricePosition = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerEnd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                endPricePosition = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

     /*   listviewStart.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startPricePosition = position + 1;

            }
        });

        listviewEnd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                endPricePosition = position + 1;
            }
        });
*/
        btnSend.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                //زمانی که بر روی دکمه کلیک شد


                //گزفتن ورودی و ذخیره در متغیر
                if (!edtInput.getText().toString().isEmpty()){
                    price = Float.parseFloat(edtInput.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this, "لطفا مبلغ را وارد نمایید", Toast.LENGTH_SHORT).show();
                }


                if (startPricePosition == endPricePosition) {
                    Toast.makeText(MainActivity.this, "هر دو واحد یکی است", Toast.LENGTH_SHORT).show();
                } else {

                    switch (startPricePosition) {
                        case 1: {
                            if (endPricePosition == 2) {
                                dollarToEuro(price);
                            } else {
                                dollarToRial(price);
                            }
                            break;
                        }

                        case 2: {
                            if (endPricePosition == 1) {
                                euroToDollar(price);
                            } else {
                                euroToRial(price);

                            }
                            break;
                        }

                        case 3: {
                            if (endPricePosition == 1) {
                                rialToDollar(price);
                            } else {

                                rialToEuro(price);
                            }
                            break;
                        }
                    }
                }

              if (!edtInput.getText().toString().isEmpty()&&price!=0){

                  txtResult.setText("نتیجه:"+result);

                  price=0;
              }

            }
        });

    }



    //تبدیل ارز
    private void rialToDollar(float price) {


        result = String.valueOf(price / 420f);

    }

    private void dollarToRial(float price) {

        result = String.valueOf(price * 420f);

    }


    private void dollarToEuro(float price) {

        result = String.valueOf(price * 121f);


    }

    private void euroToDollar(float price) {

        result = String.valueOf(price / 121f);

    }

    private void rialToEuro(float price) {

        Log.i("kk","result rial to euro"+result);
        result = String.valueOf(price / 0.2f);
    }

    private void euroToRial(float price) {

        result = String.valueOf(price * 0.2f);
    }


}