package anflo.formularioandroid;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by tono on 26/09/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
