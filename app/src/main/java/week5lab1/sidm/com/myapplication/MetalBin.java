package week5lab1.sidm.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class MetalBin implements EntityBase, Collidable
{
    private Bitmap [] bmp = new Bitmap[1];
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;
    private short animIndex = 0;
    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;

    }

    @Override
    public void Init(SurfaceView _view) {
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metal_bin);
    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(bmp[animIndex], xPos - bmp[animIndex].getWidth() * 0.5f,
                yPos - bmp[animIndex].getHeight() * 0.5f, null);

    }

    public static  MetalBin Create(int posx, int posy)
    {
        MetalBin result = new MetalBin();
        result.xPos = posx;
        result.yPos = posy;
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType()
    {
        return "MetalBin";
    }

    @Override
    public float GetPosX()
    {
        return xPos;
    }

    @Override
    public float GetPosY()
    {
        return yPos;
    }

    @Override
    public float GetRadius()
    {
        return bmp[animIndex].getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other)
    {
        if(_other.GetType() == "SampleEntity")
        {
            //SetIsDone(true);
        }
    }
}
