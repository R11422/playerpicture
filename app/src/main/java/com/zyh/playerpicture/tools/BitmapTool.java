package com.zyh.playerpicture.tools;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author created by 张永宏
 * date :2021/6/28 3:46 PM
 */
public class BitmapTool {
    public static List<String> getAssetPicPath(Context context){
        AssetManager am = context.getAssets();
        String[] path = null;
        try {
            path = am.list("");  // ""获取所有,填入目录获取该目录下所有资源
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> pciPaths = new ArrayList<>();
        for(int i = 0; i < path.length; i++){
            if (path[i].endsWith(".png") ){  // 根据图片特征找出图片
                pciPaths.add(path[i]);
            }
        }
        return pciPaths;
    }
    public static Bitmap getAssetsBitmap(Context context, String path){
        AssetManager am = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = am.open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        return bitmap;
    }



}
