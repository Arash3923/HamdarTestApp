package com.hamdartestapp.Utility;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class FileSaver {

    private final Context context;

    public FileSaver(Context context) {
        this.context = context;
    }

    public boolean saveTextToFile(String fileName, String text) {
        try {
            File file = createFile(fileName);
            if (file == null) {
                showToast("خطا در ایجاد فایل");
                return false;
            }

            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
            osw.write(text);
            osw.flush();
            osw.close();
            fos.close();

            showToast("فایل با موفقیت ذخیره شد: " + file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            showToast("خطا در ذخیره فایل: " + e.getMessage());
            return false;
        }
    }

    private File createFile(String fileName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return createFileForAndroid10AndAbove(fileName);
        } else {
            return createFileForAndroid9AndBelow(fileName);
        }
    }

    private File createFileForAndroid10AndAbove(String fileName) {
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs();
        }
        return new File(downloadsDir, fileName);
    }

    private File createFileForAndroid9AndBelow(String fileName) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        File downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!downloadsDir.exists()) {
            downloadsDir.mkdirs();
        }
        return new File(downloadsDir, fileName);
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}