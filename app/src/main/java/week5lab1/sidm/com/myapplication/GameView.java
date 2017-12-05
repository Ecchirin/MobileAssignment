package week5lab1.sidm.com.myapplication;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{
    private SurfaceHolder holder = null;
    // TODO: UpdateThread
    private UpdateThread updateThread = new UpdateThread(this);

    public GameView(Context _context)
    {
        super(_context);
        holder = getHolder();

        if(holder != null)
        {
            holder.addCallback(new SurfaceHolder.Callback()
            {
                @Override
                public void surfaceCreated(SurfaceHolder holder)
                {
                    // Set up stuff
                     if(!updateThread.IsRunning())
                     {
                         updateThread.Initialize();
                     }
                     if(!updateThread.isAlive())
                     {
                         updateThread.start();
                     }

                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format,
                                           int width, int height)
                {
                    // Nothing to do here
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder)
                {
                    // TODO: same for update thread is done
                    // updateThread.Terminate();
                }
            });
        }
    }
}

