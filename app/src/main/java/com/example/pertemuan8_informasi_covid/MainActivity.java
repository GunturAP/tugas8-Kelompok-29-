package com.example.pertemuan8_informasi_covid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pertemuan8_informasi_covid.api.ApiUtilities;
import com.example.pertemuan8_informasi_covid.api.DataProvinsi;
import com.example.pertemuan8_informasi_covid.api.ListProvinsi;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener{
    private TextView totalKonfirmasi, totaldirawat, totalSembuh, totalMeninggal, totalTes;
    private TextView konfirmasiHariini, sembuhHariini, meninggalHariini, dateTV;

    private PieChart pieChart;
    private Spinner spiner ;
    private String prov;
    private List<DataProvinsi> listh;
    private List<ListProvinsi> listp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listh = new ArrayList<>();
        listp = new ArrayList<>();
        init();
    }



    private void init(){
        totalKonfirmasi = findViewById(R.id.tvTotalConfirm);
        totaldirawat = findViewById(R.id.tvTotalActive);
        totalSembuh = findViewById(R.id.tvTotalRecovered);
        totalMeninggal = findViewById(R.id.tvTotalDeath);
        konfirmasiHariini = findViewById(R.id.tvTodayConfirm);
        sembuhHariini= findViewById(R.id.tvTodayRecovered);
        meninggalHariini = findViewById(R.id.tvTodayDeath);
        pieChart=findViewById(R.id.piechart);
        dateTV=findViewById(R.id.tvUpdated);
        spiner=findViewById(R.id.countryName);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
              R.array.daftar_kota, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.color_spinner_layout);
        spiner.setOnItemSelectedListener(this);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(0xFFFFFFFF);
        ((TextView) view).setTextSize(20);

        String text = parent.getItemAtPosition(position).toString();
        prov = text;
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void lihatkasus(View view) {

        ApiUtilities.getApiInterface().getJson()
                .enqueue (new Callback<DataProvinsi>() {
                    @Override
                    public void onResponse(Call <DataProvinsi> call, Response<DataProvinsi> response) {
                        listp.addAll(response.body().getList_data());
                        String tgl = response.body().getLast_date();



                        for (int i=0; i<listp.size();i++){
                            if(listp.get(i).getKey().equals(prov)){
                                int konfirmasi = Integer.parseInt(listp.get(i).getJumlah_kasus());
                                int dirawat = Integer.parseInt(listp.get(i).getJumlah_dirawat());
                                int sembuh = Integer.parseInt(listp.get(i).getJumlah_sembuh());
                                int meninggal = Integer.parseInt(listp.get(i).getJumlah_meninggal());
                                int meninggalhr = Integer.parseInt(listp.get(i).getJumlah_meninggal());

                                totalKonfirmasi.setText(NumberFormat.getInstance().format(konfirmasi));
                                totaldirawat.setText(NumberFormat.getInstance().format(dirawat));
                                totalSembuh.setText(NumberFormat.getInstance().format(sembuh));
                                totalMeninggal.setText(NumberFormat.getInstance().format(meninggal));
                                dateTV.setText("Di Update Pada "+tgl);

                                pieChart.clearChart();
                                pieChart.addPieSlice(new PieModel("Konfirmasi", konfirmasi, getResources().getColor(R.color.yellow)));
                                pieChart.addPieSlice(new PieModel("Dirawat", dirawat, getResources().getColor(R.color.blue_pie)));
                                pieChart.addPieSlice(new PieModel("Sembuh", sembuh, getResources().getColor(R.color.green_pie)));
                                pieChart.addPieSlice(new PieModel("Meninggal", meninggal, getResources().getColor(R.color.red_pie)));
                                pieChart.startAnimation();

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<DataProvinsi> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "ERROR "+t.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

    }

}