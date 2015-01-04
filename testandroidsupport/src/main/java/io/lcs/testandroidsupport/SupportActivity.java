package io.lcs.testandroidsupport;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Calendar;


public class SupportActivity extends Activity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_support);

		findViewById(R.id.supportV4).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new DatePicker().show(getFragmentManager(),"shit");
			}
		});
		findViewById(R.id.supportV4_2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			new DatePickerDialog( v.getContext() , new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

				}
			},4,5,6).show();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_support, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public static class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {


		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			Calendar c = Calendar.getInstance();

			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
		@Override
		public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {

		}
	}
}
