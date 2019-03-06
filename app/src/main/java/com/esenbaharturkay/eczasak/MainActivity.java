package com.esenbaharturkay.eczasak;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button isim;//isim butonu

    TextView textView1;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 = findViewById(R.id.textView1);
        imageView=findViewById(R.id.imageView);
        isim=(Button) findViewById(R.id.isim);
        isim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new Isim().execute();
            }
        });



    }

    private class Isim extends AsyncTask<Void,Void,Void>
    {
        String URL="http://www.sakarya.bel.tr/1/EBelediye/NobetciEczaneler";
        String  isim;
        String  veri;
        List<Eczane> eczaneler=new ArrayList<Eczane>();
        @Override
        protected Void doInBackground(Void... params) {

            try {
                Document doc= Jsoup.connect(URL).get();//Siteye bağlantı sağlanıyor.

                Elements ElmEczaneler=doc.select("td[class=nobetciEczane]");
                for(Element element:ElmEczaneler) {
                    Eczane eczane=new Eczane();
                    String[] str=element.html().split("<br>");
                    eczane.setAdi(str[0]);
                    eczane.setAdress(str[1]);
                    eczane.setTelefon(str[2]);
                    eczaneler.add(eczane);
                }


                //isim=Jsoup.parse(veri).text();//html taglarını texte çevirir.



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void avoid)
        {
            String str="";
            for(Eczane eczane:eczaneler) {
                Intent i = getIntent();
//JSF ile düzenle.
                str+=eczane.getAdi()+" \n "+eczane.getAdress()+ " \n "+eczane.getTelefon()+"\n"+"\n"+"\n";
                textView1.setText(str);
                //System.out.println(eczane.getAdi()+" "+eczane.getAdress()+ " "+eczane.getTelefon());
            }





        }

    }




}
