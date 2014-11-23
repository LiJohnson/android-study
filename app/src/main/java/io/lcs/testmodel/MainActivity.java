package io.lcs.testmodel;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.db.chart.model.LineSet;
import com.db.chart.model.Point;
import com.db.chart.view.LineChartView;
import com.db.chart.view.YController;
import com.enrique.stackblur.StackBlurManager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Date;

import io.lcs.testmodel.greendao.DaoMaster;
import io.lcs.testmodel.greendao.Note;
import io.lcs.testmodel.greendao.NoteDao;
import io.lcs.testmodel.greendao.TestBean;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements OnRefreshListener {

	@ViewById(R.id.imageView)
	protected ImageView imageView;

	@ViewById(R.id.btn)
	protected Button btn;

	@ViewById(R.id.ptr_view)
	protected PullToRefreshLayout pullToRefreshLayout;

	@Bean
	TestBean customer;


	@ViewById(R.id.line_chart_view)
	public LineChartView lineChartView;

	private NoteDao noteDao;

	private StackBlurManager stackBlurManager;


//	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.noteDao = new DaoMaster(new DaoMaster.DevOpenHelper(this, "notes-db", null).getWritableDatabase()).newSession().getNoteDao();
		this.stackBlurManager =  new StackBlurManager(BitmapFactory.decodeStream(this.getResources().openRawResource(R.drawable.ic_launcher)));

		Log.i("shit" , this.customer + "");
	}
	@AfterViews
	public void viewInited() {
		if (this.pullToRefreshLayout == null) {
			Log.i("shit","this.pullToRefreshLayout is null");
			this.pullToRefreshLayout =(PullToRefreshLayout) this.findViewById(R.id.ptr_view);
			if (this.pullToRefreshLayout == null) {
				Log.i("shit","this.pullToRefreshLayout still is null ");
				return;
			}

		}
		ActionBarPullToRefresh.from(this)
				// Mark All Children as pullable
				.allChildrenArePullable()
						// Set a OnRefreshListener
				.listener(this)
						// Finally commit the setup to our PullToRefreshLayout
				.setup(this.pullToRefreshLayout);


	}
	@Click(R.id.btn)
	public void click(){
		int proces = (int)(Math.random()*50);
		this.noteDao.insert(new Note( null , proces+"" , null , null  ));
		this.updateImg(proces);
		this.updateBtn(proces);
		Log.i("shit", this.imageView.toString());

	}
	@UiThread
	public void updateImg( Bitmap bitmap ){
		this.imageView.setImageBitmap(bitmap);
	}
	@Background
	public void updateImg(int process){
		this.updateImg( this.stackBlurManager.process( process ) );
	}
	@UiThread
	public void updateBtn( int process ){
		this.btn.setText( "process" + process + " : " + this.noteDao.count());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

	@Override
	@Background
	public void onRefreshStarted(View view) {
		Log.i("shit","start : " + new Date());
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {	}
		Log.i("shit","end : " + new Date());
		this.updateLineChart( 3,10 );
	}

	@UiThread
	public void updateLineChart(int nSets, int nPoints){
		LineSet data = null;
		this.lineChartView.reset();

		for(int i = 0; i < nSets; i++){
			data = new LineSet();
			for(int j = 0; j < nPoints; j++) {
				data.addPoint(new Point( j + "", DataRetriever.randNumber( 10 ,30 )  ));
			}

			data.setDots(DataRetriever.randBoolean())
					.setDotsColor(Color.parseColor(DataRetriever.getColor(DataRetriever.randNumber(0,2))))
					.setDotsRadius(DataRetriever.randDimen(4, 7))
					.setLineThickness(DataRetriever.randDimen(3, 8))
					.setLineColor(Color.parseColor(DataRetriever.getColor(i)))
					.setDashed(DataRetriever.randBoolean())
					.setSmooth(DataRetriever.randBoolean())
			;

			if(i == 2){
				//data.setFill(Color.parseColor("#3388c6c3"));
				int[] colors = {Color.parseColor("#3388c6c3"), Color.TRANSPARENT};
				data.setGradientFill(colors, null);
			}

			if(DataRetriever.randBoolean())
				data.setDotsStrokeThickness(DataRetriever.randDimen(1,4))
						.setDotsStrokeColor(Color.parseColor(DataRetriever.getColor(DataRetriever.randNumber(0,2))))
						;

		 this.lineChartView.addData(data);
		}

		this.lineChartView.setGrid(DataRetriever.randPaint())
				.setVerticalGrid(DataRetriever.randPaint())
				.setHorizontalGrid(DataRetriever.randPaint())
						//.setThresholdLine(2, randPaint())
				.setYLabels(YController.LabelPosition.NONE)
				.setYAxis(false)
				.setXLabels(DataRetriever.getXPosition())
				.setXAxis(DataRetriever.randBoolean())
				.setMaxAxisValue(30, 2)
				.animate(DataRetriever.randAnimation(new Runnable() {
					@Override
					public void run() {

					}
				}, nPoints))
		//.show()
		;
		this.pullToRefreshLayout.setRefreshComplete();
	}
}
