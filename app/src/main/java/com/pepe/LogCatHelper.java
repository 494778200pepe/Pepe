package com.pepe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

/**
 * @author Administrator
 *         <p>
 *         log打印日志保存,文件的保存以小时为单位
 *         permission:<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 *         <uses-permission android:name="android.permission.READ_LOGS" />
 */
public class LogCatHelper {
    private static final String TAG = "log_helper";
    private static LogCatHelper instance = null;
    private String dirPath;//保存路径
    private int appid;//应用pid
    private Thread logThread;

    /**
     * @param mContext
     * @return
     */
    public static LogCatHelper getInstance(Context mContext) {
        if (instance == null) {
            instance = new LogCatHelper(mContext, "mnt/sdcard/data/app");
        }
        return instance;
    }

    private LogCatHelper(Context mContext, String path) {
        appid = android.os.Process.myPid();
        if (TextUtils.isEmpty(path)) {
            dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "seeker" + File.separator + mContext.getPackageName();
            Log.d(TAG, "=====> path = " + dirPath);
        } else {
            dirPath = path;
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 启动log日志保存
     */
    public void start() {
        if (logThread == null) {
            logThread = new Thread(new LogRunnable(appid, dirPath));
        }
        logThread.start();
    }

    private static class LogRunnable implements Runnable {

        private Process mProcess;
        private FileOutputStream fos;
        private BufferedReader mReader;
        private String cmds;
        private String mPid;

        public LogRunnable(int pid, String dirPath) {
            this.mPid = Integer.toString(pid);
            try {
                File file = new File(dirPath, FormatDate.getFormatDate() + "_video.log");
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file, true);
            } catch (Exception e) {
                Log.d(TAG, "error ： " + e);
            }
            cmds = "logcat *:v | grep \"(" + mPid + ")\"";
        }

        @Override
        public void run() {
            try {
                mProcess = Runtime.getRuntime().exec(cmds);
                mReader = new BufferedReader(new InputStreamReader(mProcess.getInputStream()), 1024);
                String line;
                while ((line = mReader.readLine()) != null) {
                    if (line.length() == 0) {
                        continue;
                    }
                    if (fos != null && line.contains(mPid)) {
                        fos.write((FormatDate.getFormatTime() + " " + line + "\r\n").getBytes());
                    }
                }
            } catch (Exception e) {
                Log.d(TAG, "error ： " + e);
            } finally {
                if (mProcess != null) {
                    mProcess.destroy();
                    mProcess = null;
                }
                try {
                    if (mReader != null) {
                        mReader.close();
                        mReader = null;
                    }
                    if (fos != null) {
                        fos.close();
                        fos = null;
                    }
                } catch (Exception e2) {
                    Log.d(TAG, "error ： " + e2);
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static class FormatDate {

        public static String getFormatDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
            return sdf.format(System.currentTimeMillis());
        }

        public static String getFormatTime() {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(System.currentTimeMillis());
        }
    }
}