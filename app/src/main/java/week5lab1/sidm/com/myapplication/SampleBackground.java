package week5lab1.sidm.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleBackground implements  EntityBase
{
    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    float offset, xPos, yPos = 0.0f;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone =_isDone;

    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.gamescene2);
        view = _view;
        offset = 0.0f;
    }

    @Override
    public void Update(float _dt) {
            //offset += _dt * 0.1;
    }

    @Override
    public void Render(Canvas _canvas) {
        xPos = 0.5f * view.getWidth();
        yPos = 0.5f * view.getHeight();
        float xOffset = (float) Math.sin(offset) * bmp.getWidth() * 0.3f;
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f + xOffset,
                yPos - bmp.getHeight() * 0.5f, null);

    }


    public static SampleBackground Create()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
