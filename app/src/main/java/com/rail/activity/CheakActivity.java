package com.rail.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.rail.https.BookTicket;
import com.rail.https.BookTicketSix;
import com.rail.https.Login;
import com.rail.https.Loginfour;

public class CheakActivity extends Activity {
    private ImageView imageView;
    private String s=null;
    private TextView textView_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheak);
        imageView = (ImageView) this.findViewById(R.id.id_imageview_check);
        textView_show = (TextView) this.findViewById(R.id.showtextbiew);
        final EditText textView = (EditText) this.findViewById(R.id.tv_check);
        imageView.setImageResource(R.drawable.btn_change);
        Button button_book = (Button) this.findViewById(R.id.button_book);
        Button button_book1 = (Button) this.findViewById(R.id.button_book1);
        button_book1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(textView.getText());
                new BookTicketSix().bookticketsix("rand=randp&randCode=" + textView.getText() + "REPEAT_SUBMIT_TOKEN=" + s + "&_json_att=");
            }
        });
        button_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new BookTicket(CheakActivity.this).bookticket("MjAxNS0xMi0wNCMwMCNHMTAxIzA1OjM3IzA3OjAwIzI0MDAwMEcxMDEwQSNWTlAjQU9IIzEyOjM3I+WMl+S6rOWNlyPkuIrmtbfombnmoaUjMDEjMTAjTzA1NTMwMDY0M00wOTMzMDAwMDM5MTc0ODAwMDEwI1AyIzE0NDg5NzU4ODY2MjIjMTQ0NDEwNTgwMDAwMCM0NkM5MUU5NjY3NzVCMENFRUY0MjU0MDNGMjlENENGNjVDRDNGQkU2QjQ2OTRENTlBOTMwOTk1MQ=="
                        + "&train_date=2015-12-04&back_train_date=2015-12-04&tour_flag=dc"
                        + "purpose_codes=ADULT&query_from_station_name=北京"
                        + "&query_to_station_name=上海虹桥&undefined=");
            }
        });
        Button submit = (Button) this.findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = textView.getText().toString();
                new Loginfour().loginfour(s, textView_show);
            }
        });
        new Login(this).login();
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            byte[] bys = (byte[]) msg.obj;
            if(TextUtils.isEmpty(msg.getData().getString("string")))
                s=msg.getData().getString("string");
            System.out.println("StringsData:"+s);
            final Bitmap bitmap = BitmapFactory.decodeByteArray(bys, 0, bys.length);
            imageView.setImageBitmap(bitmap);
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    event.getX();
                    event.getY();
                    if (event.getX() <= bitmap.getWidth() && event.getX() >= 0 && event.getY() >= 0 && event.getY() <= bitmap.getHeight()) {

                    }
                    return false;
                }
            });
        }
    };
}
