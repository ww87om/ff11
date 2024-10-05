package com.example.yum.until;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * 图片管理--保存与加载
 */
public class FileImgUntil {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    // 异步保存位图
    public static Future<Void> saveBitmapAsync(final Bitmap bitmap, final String path) {
        return executorService.submit(() -> {
            saveBitmapToFile(bitmap, path);
            return null;
        });
    }

    // 保存位图到指定文件
    private static void saveBitmapToFile(Bitmap bitmap, String path) {
        File file = new File(path);
        try (FileOutputStream fos = new FileOutputStream(file)) { // 使用 try-with-resources 确保关闭
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos); // 将位图以 PNG 格式压缩并写入文件
        } catch (IOException e) {
            e.printStackTrace(); // 捕获并打印异常
        }
    }

    // 从 Uri 保存位图到指定文件
    public static void saveImageBitmapToFileImg(Uri uri, Context context, String path) {
        Glide.with(context)
                .asBitmap()
                .load(uri) // 加载指定的 Uri
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        saveBitmapToFile(resource, path); // 调用保存位图的方法
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {}
                });
    }

    // 生成图片文件名
    public static String getImgName() {
        String imgName = "/" + UUID.randomUUID().toString().replace("-", "") + ".png"; // 使用 UUID 生成唯一文件名
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + imgName; // 获取绝对路径
    }

    // 保存系统图像到指定路径
    public static void saveSystemImgToPath(Context context, int id, String path) {
        Drawable drawable = ContextCompat.getDrawable(context, id); // 获取资源中的 Drawable
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap(); // 将 Drawable 转换为 Bitmap
        saveBitmapAsync(bitmap, path); // 异步保存位图
    }

    // 保存 Bitmap 到指定路径
    public static void saveSystemImgToPath(Bitmap bitmap, String path) {
        saveBitmapAsync(bitmap, path); // 异步保存位图
    }
}