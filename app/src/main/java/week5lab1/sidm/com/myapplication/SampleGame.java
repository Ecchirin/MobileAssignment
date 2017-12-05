package week5lab1.sidm.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import java.util.Random;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();
    private Bitmap topFrameAsset;
    // Game stuff
    private Bitmap bmp;
    float offset = 0.0f;
    float timer;
    private int currScore;
    private int currCombo;
    private int scoreMultiplier;
    private int currLife;
    Paint theScore;
    Paint theTopFrame;
    Paint theHP;
    Paint theCombo;

    public void setScore(int value)
    {
        currScore += (value * scoreMultiplier);
    }

    public void resetCombo()
    {
        currCombo = 0;
    }

    public void addCombo()
    {
        currCombo ++;
    }

    public void gotWrong()
    {
        minusLife();
        resetCombo();
        setScore(-100);
    }

    public void gotRight()
    {
        addCombo();
        setScore(100);
    }

    public void minusLife()
    {
        currLife--;
    }

    private SampleGame()
    {

    }
    public void Init(SurfaceView _view)
    {
        topFrameAsset = BitmapFactory.decodeResource(_view.getResources(), R.drawable.topframe);

        theScore = new Paint(Paint.ANTI_ALIAS_FLAG);
        theScore.setColor(Color.rgb(255,255,255));
        theScore.setTextSize(80);
        theHP = new Paint(Paint.ANTI_ALIAS_FLAG);
        theHP.setColor(Color.rgb(255,255,255));
        theHP.setTextSize(80);
        theCombo = new Paint(Paint.ANTI_ALIAS_FLAG);
        theCombo.setColor(Color.rgb(255,255,255));
        theCombo.setTextSize(80);

        theTopFrame = new Paint(Paint.ANTI_ALIAS_FLAG);

        // bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.button);
        EntityManager.Instance.Init(_view);
        SampleBackground.Create();
        MetalBin.Create(400, 850);
        PlasticBin.Create(1200, 850);
        currScore = 0;
        currCombo = 0;
        currLife = 3;
    }
    public void Update(float _deltaTime)
    {
        scoreMultiplier = currCombo;
        scoreMultiplier /= 10;

        if (scoreMultiplier <= 0)
            scoreMultiplier = 1;
        //if (currLife <= 0)
            //set game over


        // offset += _deltaTime;
        timer += _deltaTime;
        if(timer > 2.5f)
        {
            Random ranGen = new Random();

            if (ranGen.nextBoolean())
                SampleEntity.Create();
            else
                MetalBottle.Create();

            timer = 0.0f;

        }
        EntityManager.Instance.Update(_deltaTime);
    }
    public void Render(Canvas _canvas)
    {
       // int currOffset = (int)(offset * 100.f);
       // _canvas.drawBitmap(bmp, (10 + currOffset) % 500, 10, null);
        EntityManager.Instance.Render(_canvas);

        _canvas.drawBitmap(topFrameAsset, 0, 0, theTopFrame);
        _canvas.drawText("Score : " + Integer.toString(currScore), 75, 100, theScore);
        _canvas.drawText("Combo : " + Integer.toString(currCombo), 700, 100, theCombo);
        _canvas.drawText("Life : " + Integer.toString(currLife), 1300, 100, theHP);
    }
}
