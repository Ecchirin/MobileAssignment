package week5lab1.sidm.com.myapplication;

import android.view.MotionEvent;

public class TouchManager
{
    public final static TouchManager Instance = new TouchManager();

    private TouchManager()
    {}
    public enum TouchState
    {
        NONE,
        DOWN,
        MOVE
    }
    private TouchState status = TouchState.NONE;
    private int posX, posY;
    public boolean HasTouch()
    {
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }
    public boolean IsDown()
    {
        // #ifdef ANDROID Android version #endif
        // IOS Version
        // PC Version
        return status == TouchState.DOWN;
    }

    public boolean IsMove()
    {
        // #ifdef ANDROID Android version #endif
        // IOS Version
        // PC Version
        return status == TouchState.MOVE;
    }
    public int GetPosX()
    {
        return posX;
    }
    public int GetPosY()
    {
        return posY;
    }
    public void Update(int _posX, int _posY, int _motionEventStatus)
    {
        posX = _posX;
        posY = _posY;

        switch(_motionEventStatus)
        {
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;

            case MotionEvent.ACTION_UP:
                status = TouchState.NONE;
                break;
        }
    }
}
