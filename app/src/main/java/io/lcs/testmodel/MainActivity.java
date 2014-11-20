package io.lcs.testmodel;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import com.enrique.stackblur.StackBlurManager;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

import io.lcs.testmodel.greendao.DaoMaster;
import io.lcs.testmodel.greendao.Note;
import io.lcs.testmodel.greendao.NoteDao;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	@ViewById(R.id.imageView)
	protected ImageView imageView;

	@ViewById(R.id.btn)
	protected Button btn;

	private NoteDao noteDao;

	private StackBlurManager stackBlurManager;


//	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		noteDao = new DaoMaster(new DaoMaster.DevOpenHelper(this, "notes-db", null).getWritableDatabase()).newSession().getNoteDao();
		try {
			this.stackBlurManager =  new StackBlurManager(BitmapFactory.decodeStream(this.getAssets().open("3.jpg")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Click(R.id.btn)
	public void click(){
		int proces = (int)(Math.random()*50);
		this.noteDao.insert(new Note( null , proces+"" , null , null  ));
		this.update( proces );
		this.updateBtn(proces);
		Log.i("shit", this.imageView.toString());

	}

	@UiThread
	public void update( int process ){
		this.imageView.setImageBitmap(this.stackBlurManager.process( process )  );
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
}
