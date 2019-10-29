package non.shahad.funwithcolorpalette;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "PaletteL";
    private ImageView mCover;
    private View mDominent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCover = findViewById(R.id.cover);
        mDominent = findViewById(R.id.dominent);

        load("https://cdn.inprnt.com/thumbs/92/d8/92d875a35515490a0a3a7c6570508c3d.jpg");
    }

    private void load(String url){
        Glide.with(this).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                doPaletteStaffs(resource);
                return false;
            }
        }).into(mCover);
    }


    private void doPaletteStaffs(Drawable drawable){
        // get bitmap from image(drawable)
        Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

        // and generate
        Palette palette = Palette.from(bitmap).generate();

        int defaultcolor = getResources().getColor(R.color.colorPrimary);
        mDominent.setBackgroundColor(palette.getDarkVibrantColor(defaultcolor));
        changeStatusBarColor(palette.getDominantColor(defaultcolor));
    }


    private void changeStatusBarColor(int color){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(color);
    }
}
