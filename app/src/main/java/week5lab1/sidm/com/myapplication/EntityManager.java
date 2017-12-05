package week5lab1.sidm.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.LinkedList;

public class EntityManager
{
    public final static EntityManager Instance = new EntityManager();
    // Need to store entities : TODO
    private LinkedList<EntityBase>entityList = new LinkedList<EntityBase>();
    private SurfaceView view = null;

    private EntityManager()
    {
    }

    public void Init(SurfaceView _view)
    {
        // TODO
        view = _view;
    }
    public void Update(float _dt)
    {
        // TODO
        LinkedList<EntityBase>removalList = new LinkedList<EntityBase>();

        // Update every single entity in the list
        for (EntityBase currEntity : entityList)
        {
            currEntity.Update(_dt);
            // Check if it is done
            if(currEntity.IsDone())
            {
                // Done! Time to add to the removal list
                removalList.add(currEntity);
            }
        }

        // Time to remove
        for (EntityBase currEntity : removalList)
        {
            entityList.remove(currEntity);
        }
        removalList.clear();

        // Collision Check
        for(int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if(currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;

                for(int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if(currEntity instanceof  Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;
                        if (Collision.SphereToSphere(first.GetPosX(), first.GetPosY(),
                                first.GetRadius(), second.GetPosX(), second.GetPosY(),
                                second.GetRadius()))
                        {

                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }

            if(currEntity.IsDone())
            {
                removalList.add(currEntity);
            }
            removalList.clear();
        }
    }
    public void Render(Canvas _canvas)
    {
        // Render all entities here
        for (EntityBase currEntity : entityList)
        {
            currEntity.Render(_canvas);
        }
    }
    public void AddEntity(EntityBase _newEntity)
    {
        _newEntity.Init(view);
        entityList.add(_newEntity);
    }
}
