package io.lcs.daggergreendao;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;

import dagger.ObjectGraph;

/**
 * Created by lcs on 14-12-8.
 */
public class MainApp extends Application {
	private ObjectGraph objectGraph;
	private Activity currentActivity = null;
	@Override
	public void onCreate(){
		super.onCreate();
		Log.i("shit","main_app");
		objectGraph = ObjectGraph.create(new DaggerModel(this));
	}
	public void inject(Object o){
		if( o instanceof Activity ){
			this.currentActivity = (Activity)o;
		}
		this.objectGraph.inject(o);
	}
	public View getView( int id ) {
		if (this.currentActivity == null) return null;

		return this.currentActivity.findViewById(id);
	}
}
