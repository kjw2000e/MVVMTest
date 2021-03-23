package twobeone.com.mvvmtest.View;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import twobeone.com.mvvmtest.Player.ExoPlayerService;

public abstract class BaseActivity extends FragmentActivity {

    protected ExoPlayerService mService = null;
    protected boolean mBound = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //BindService
        if (!mBound) {
            Intent intent = new Intent(getApplicationContext(), ExoPlayerService.class);
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mService.release();
        unbindService(serviceConnection);
        mBound = false;
    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (!mBound) {
                ExoPlayerService.LocalBinder binder = (ExoPlayerService.LocalBinder)service;
                mService = binder.getService();
                mBound = true;

                setPlayerController(mService);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mBound = false;
        }
    };

    public abstract void setPlayerController(ExoPlayerService service);
}
