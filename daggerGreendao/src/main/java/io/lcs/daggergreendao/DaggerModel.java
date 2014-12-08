package io.lcs.daggergreendao;

import android.util.Log;

import dagger.Module;
import dagger.Provides;
import io.lcs.daggergreendao.dao.CustomerDao;
import io.lcs.daggergreendao.dao.DaoSession;
import io.lcs.daggergreendao.factory.DaoFactory;

/**
 * Created by lcs on 14-12-8.
 */
@Module(injects = {MainActivity.class} , library = true)
public class DaggerModel {
	private final MainApp app;
	private DaoFactory df;
	public  DaggerModel(MainApp app) {
		this.app = app;
		this.df = new DaoFactory(this.app);
	}
//	@Provides
//	public DaoSession pp() {
//		Log.i("shit", "pppppp");
//		return this.df.getDaoSession();
//	}

	@Provides
	public DaoSession qq() {
		Log.i("shit", "qqqqqq");
		return this.df.getDaoSession();
	}

	@Provides
	CustomerDao g( DaoSession ds ){
		Log.i("shit", "gggggggggggggggg");

		return ds.getCustomerDao();
	}
}
