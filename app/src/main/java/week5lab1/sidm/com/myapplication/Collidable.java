package week5lab1.sidm.com.myapplication;

public interface Collidable
{
    String GetType();

    float GetPosX();
    float GetPosY();
    float GetRadius();

    void OnHit(Collidable _other);

}
