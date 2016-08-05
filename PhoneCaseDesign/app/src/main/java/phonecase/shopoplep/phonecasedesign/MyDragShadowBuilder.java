package phonecase.shopoplep.phonecasedesign;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by tunglxx226 on 7/29/2016.
 */
public class MyDragShadowBuilder extends View.DragShadowBuilder {

    int width, height, relativeX, relativeY;

    public MyDragShadowBuilder(View v, float relativeX,float relativeY) {
        super(v);
        this.relativeX = (int) relativeX;
        this.relativeY = (int) relativeY;
        width = (v.getWidth());
        height = (v.getHeight());
    }

    @Override
    public void onProvideShadowMetrics (@NonNull Point size, @NonNull Point touch){
        size.set(width, height);
        touch.set(relativeX, relativeY);
    }

}