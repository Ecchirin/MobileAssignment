package week5lab1.sidm.com.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import static java.lang.System.currentTimeMillis;

public class UpdateThread extends Thread
{
    static final long targetFPS = 60;

    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();

        SampleGame.Instance.Init(view);
    }

    public boolean IsRunning()
    {
        return isRunning;
    }
    public void Initialize()
    {
        isRunning = true;
    }
    public void Terminate()
    {
        isRunning = false;
    }

    @Override
    public void run()
    {
        // THIS IS THE GAME LOOP *IMPORTANT*

        // Init here for variables needed
        long framePerSecond = 1000/targetFPS; // 1000 milliseconds -> 1 second
        long startTime = 0; // This is for FRC (Frame Rate Controller)

        long prevTime = System.nanoTime(); // THIS IS FOR DELTA TIME

        while (IsRunning())
        {
            // Update
            long currTime = System.nanoTime();
            float deltaTime = (float)((currTime - prevTime) / 1000000000.0f);
            prevTime = currTime;
            // End delta time

            SampleGame.Instance.Update(deltaTime);
            // EntityManager.Instance.Update(deltaTime);

            // Render
            Canvas canvas = holder.lockCanvas();
            if(canvas != null)
            {
                synchronized (holder) // Lock the door
                {
                    // Start doing stuff here
                    canvas.drawColor(Color.BLACK);

                    SampleGame.Instance.Render(canvas);
                    //EntityManager.Instance.Render(canvas)
                }
                holder.unlockCanvasAndPost(canvas);
            }

            // Post Update
            try
            {
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);

                if(sleepTime > 0)
                {
                    sleep(sleepTime);
                }
            }
            catch(InterruptedException e)
            {
                Terminate();
            }
        }

    }
}
