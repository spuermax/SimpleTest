package com.developers.test.common;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.developers.test.R;
import com.supermax.base.common.widget.toast.QsToast;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:20
 * @Description 相机工具类
 */
public class PhotoUtil {
    public static Uri tempUri;
    public static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    public static final int CHOOSE_PHOTO = 2;//相册

    /**
     * 打开相册
     */
    public static void openAlbum(Activity activity, boolean isShowCamera) {
//        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//        if (EasyPermissions.hasPermissions(activity, perms)) {
//            MultiImageSelector.create()
//                    .showCamera(isShowCamera)
//                    .single()
//                    .start(activity, CHOOSE_PHOTO);
//        } else {
//            EasyPermissions.requestPermissions(
//                    new PermissionRequest.Builder(activity, RC_STORAGE, perms)
//                            .setRationale(activity.getString(R.string.permission_write_external_storage))
//                            .setPositiveButtonText(activity.getString(R.string.confirm))
//                            .setNegativeButtonText(activity.getString(R.string.cancel))
//                            .setTheme(R.style.Theme_AppCompat_Light_Dialog_Alert)
//                            .build());
//        }
    }

    /**
     * 打开相机拍照
     */
//    public static void openCamera(Activity activity) {
//        if (EasyPermissions.hasPermissions(activity, Manifest.permission.CAMERA)) {
//            File tempFile;
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            String filename = DateTimeUtil.getFormatToday("yyyy_MM_dd_HH_mm_ss");
//            tempFile = new File(activity.getExternalCacheDir(), filename + ".jpg");
//
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
//                tempUri = Uri.fromFile(tempFile);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//            } else {
//                ContentValues contentValues = new ContentValues(1);
//                contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
//                tempUri = FileProvider.getUriForFile(activity, "com.edusoho.newcuour.fileProvider", tempFile);
//
//                FileProviderUtil.grantUriPermission(activity, intent, tempUri);
//                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
//            }
//
//            try {
//                activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
//            } catch (ActivityNotFoundException anf) {
//                QsToast.show("摄像头未准备好！");
//            }
//        } else {
//            EasyPermissions.requestPermissions(
//                    new PermissionRequest.Builder(activity, RC_CAMERA, Manifest.permission.CAMERA)
//                            .setRationale(activity.getString(R.string.permission_camera))
//                            .setPositiveButtonText(activity.getString(R.string.confirm))
//                            .setNegativeButtonText(activity.getString(R.string.cancel))
//                            .setTheme(R.style.Theme_AppCompat_Light_Dialog_Alert)
//                            .build());
//        }
//    }

    /**
     * 裁剪
     */
//    public static void cropPhoto(Activity activity, Uri uri) {
//        String filename = DateTimeUtil.getFormatToday("yyyy_MM_dd_HH_mm_ss");
//        File tempCropFile = new File(activity.getExternalCacheDir(), filename + ".jpg");
//
//        UCrop.Options options = new UCrop.Options();
//        options.setStatusBarColor(0xFF000000);
//        options.setToolbarColor(0xFF282828);
//
//
//        UCrop.of(uri, Uri.fromFile(tempCropFile))
//                .withAspectRatio(1, 1)
//                .withMaxResultSize(300, 300)
//                .withOptions(options)
//                .start(activity);
//    }
}

