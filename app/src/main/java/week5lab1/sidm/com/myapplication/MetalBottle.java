package week5lab1.sidm.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class MetalBottle implements EntityBase, Collidable
{
    private Bitmap [] bmp = new Bitmap[3];
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
        bmp[0] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metalcan);
        bmp[1] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metalcan2);
        bmp[2] = BitmapFactory.decodeResource(_view.getResources(), R.drawable.metalcan3);

        lifeTime = 30.0f;
        Random ranGen = new Random();
        animIndex = (short)ranGen.nextInt(3);
        xPos = _view.getWidth();
        yPos = 250.f;

        xDir = 150.f;
        // yDir = ranGen.nextFloat() * 100.0f - 50.0f;

    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;
        if(lifeTime < 0.0f)
        {
            SetIsDone(true);
        }


        // If user click on object, remove the object
        // Handle the touch and check collisions with click/touch
        float imgRadius = bmp[animIndex].getHeight() * 0.5f;
        if((TouchManager.Instance.IsDown() || TouchManager.Instance.IsMove()) && Collision.SphereToSphere(TouchManager.Instance.GetPosX(),
                TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius)) {

            // Collision
            //SetIsDone(true);
            xPos = TouchManager.Instance.GetPosX();
            yPos = TouchManager.Instance.GetPosY();

        }
        else {
            xPos -= xDir * _dt;
            //yPos += yDir * _dt;
        }
    }

    @Override
    public void Render(Canvas _canvas)
    {
        _canvas.drawBitmap(bmp[animIndex], xPos - bmp[animIndex].getWidth() * 0.5f,
                yPos - bmp[animIndex].getHeight() * 0.5f, null);

    }

    public static  MetalBottle Create()
    {
        MetalBottle result = new MetalBottle();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType()
    {
        return "SampleEntity";
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
        if(_other.GetType() == "MetalBin")
        {
            SetIsDone(true);
            //Add Score
            SampleGame.Instance.gotRight();
        }
        else if(_other.GetType() == "PlasticBin")
        {
            SetIsDone(true);
            //Minus Score
            SampleGame.Instance.gotWrong();
        }
    }
}
