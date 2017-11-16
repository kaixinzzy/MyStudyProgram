package cn.com.zzy;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import cn.com.zzy.databinding.ActivityDataBindingBinding;
import cn.com.zzy.model.User;

public class DataBindingActivity extends Activity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        user = new User();
        binding.setUser(user);

        user.setFirstName("one");
        user.setLastName("two");

        user.setIconUrl("http://c.hiphotosbaidu.com/image/h%3D300/sign=35b7ed538b26cffc762ab9b289034a7d/b3fb43166d224f4aa256e8d900f790529922d129.jpg");
        user.setIconPlaceHolder(R.drawable.weixin);
//        user.setIconError(R.drawable.baifubao);

        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.weixin);
        user.setIconBitmap(bitmap);



    }
}
