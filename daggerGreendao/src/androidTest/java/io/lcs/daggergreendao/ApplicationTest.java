package io.lcs.daggergreendao;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import io.lcs.daggergreendao.factory.DaoFactory;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
	public ApplicationTest() {
		super(Application.class);
	}

	public void testFac(){
		DaoFactory df = new DaoFactory( this.getContext() );
		Log.i("shit",  df.getDaoSession().getNoteDao().toString());
	}
}