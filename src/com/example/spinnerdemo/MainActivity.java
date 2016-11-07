package com.example.spinnerdemo;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, OnItemSelectedListener {

	private TextView tvCity;
	private EditText etCity;
	private Button btnAdd;
	private Button btnRemove;
	private String[] Citys = { "重庆", "上海", "厦门", "南昌" };
	private List<String> cityList;
	private ArrayAdapter<String> adapter;
	private Spinner spinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvCity = (TextView) findViewById(R.id.tvCity);
		etCity = (EditText) findViewById(R.id.etCity);
		btnAdd = (Button) findViewById(R.id.btnAdd);
		btnRemove = (Button) findViewById(R.id.btnRemove);
		spinner = (Spinner) findViewById(R.id.spinner);

		cityList = new ArrayList<String>();
		for (int i = 0; i < Citys.length; i++) {
			cityList.add(Citys[i]);
		}

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cityList);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		tvCity.setText(cityList.get(0));

		btnAdd.setOnClickListener(this);
		btnRemove.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		String newCity = etCity.getText().toString().trim();
		switch (v.getId()) {
		case R.id.btnAdd:
			if (!newCity.equals("")) {
				for (int i = 0; i < adapter.getCount(); i++) {
					if (newCity.equals(adapter.getItem(i)))
						return;
				}
				adapter.add(newCity);
				int position = adapter.getPosition(newCity);
				spinner.setSelection(position);
				etCity.setText("");
				tvCity.setText(newCity);
			}
			break;

		case R.id.btnRemove:
			if (spinner.getSelectedItem() != null) {
				adapter.remove(spinner.getSelectedItem().toString());
				spinner.setSelection(adapter.getCount());
				if (adapter.getCount() == 0)
					tvCity.setText("");
			}
			break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		tvCity.setText(adapter.getItem(position));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

}
