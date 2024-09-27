package de.androidcrypto.android_hce_emulate_a_creditcard;

import android.content.Context;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Service class to work with all file related methods writing to and reading from
 * Internal Storage of the app.
 * This is useful to send instructions to the service and for the service to know
 * what to do.
 * As this is not an activity or fragment the class do not has a Context available,
 * you need to pass the content to each method.
 */

public class InternalFilesHelper {

    private static final String TAG ="IFH"; // Internal Files Helper

    /**
     * writes a string to the filename in internal storage, If a subfolder is provided the file is created in the subfolder
     * if the file is existing it will be overwritten
     *
     * @param context
     * @param filename
     * @param subfolder
     * @param data
     * @return true if writing is successful and false if not
     */
    public static boolean writeTextToInternalStorage(@NonNull Context context, @NonNull String filename, String subfolder, @NonNull String data) {
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), filename);
        } else {
            File subfolderFile = new File(context.getFilesDir(), subfolder);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }
            file = new File(subfolderFile, filename);
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.append(data);
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * read a file from internal storage and return the content as byte array
     *
     * @param context
     * @param filename
     * @param subfolder
     * @return the content as String
     */
    public static byte[] readBinaryDataFromInternalStorage(@NonNull Context context, @NonNull String filename, String subfolder) {
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), filename);
        } else {
            File subfolderFile = new File(context.getFilesDir(), subfolder);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }
            file = new File(subfolderFile, filename);
        }
        String completeFilename = concatenateFilenameWithSubfolder(filename, subfolder);
        if (!fileExistsInInternalStorage(context, completeFilename)) {
            return null;
        }
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            in.read(bytes);
            in.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * writes a byte array to the filename in internal storage, If a subfolder is provided the file is created in the subfolder
     * if the file is existing it will be overwritten
     *
     * @param context
     * @param filename
     * @param subfolder
     * @param data
     * @return true if writing is successful and false if not
     */
    private static boolean writeBinaryDataToInternalStorage(@NonNull Context context, @NonNull String filename, String subfolder, @NonNull byte[] data) {
        final int BUFFER_SIZE = 8096;
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), filename);
        } else {
            File subfolderFile = new File(context.getFilesDir(), subfolder);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }
            file = new File(subfolderFile, filename);
        }
        try (
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                FileOutputStream out = new FileOutputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int nread;
            while ((nread = in.read(buffer)) > 0) {
                out.write(buffer, 0, nread);
            }
            out.flush();
        } catch (IOException e) {
            android.util.Log.e(TAG, "ERROR on encryption: " + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * copies a file in internal storage
     *
     * @param sourceFilename
     * @param sourceSubfolder
     * @param destFilename
     * @param destSubfolder
     * @return true if copy was successful or false if an error occurs
     */
    public static boolean copyFileInInternalStorage(@NonNull Context context, @NonNull String sourceFilename, String sourceSubfolder, @NonNull String destFilename, String destSubfolder) {
        File sourceFile, destFile;
        if (TextUtils.isEmpty(sourceSubfolder)) {
            sourceFile = new File(context.getFilesDir(), sourceFilename);
        } else {
            File subfolderFile = new File(context.getFilesDir(), sourceSubfolder);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }
            sourceFile = new File(subfolderFile, sourceFilename);
        }
        if (TextUtils.isEmpty(destSubfolder)) {
            destFile = new File(context.getFilesDir(), destFilename);
        } else {
            File subfolderFile = new File(context.getFilesDir(), destSubfolder);
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }
            destFile = new File(subfolderFile, destFilename);
        }
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            inStream = new FileInputStream(sourceFile);
            outStream = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024]; // buffer size
            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            inStream.close();
            outStream.close();
            return true;

        } catch (IOException e) {
            //Here you suppose to handle exception to figure out what went wrong
            return false;
        }
    }

    /**
     * checks if a file in internal storage is existing
     *
     * @param completeFilename with all subfolders
     * @return true if file exists and false if not
     */
    private static boolean fileExistsInInternalStorage(@NonNull Context context, String completeFilename) {
        File file = new File(context.getFilesDir(), completeFilename);
        return file.exists();
    }

    /**
     * deletes a file in internal storage
     *
     * @param completeFilename with all subfolders
     * @return true if deletion was successful
     */
    private static boolean fileDeleteInInternalStorage(@NonNull Context context, String completeFilename) {
        File file = new File(context.getFilesDir(), completeFilename);
        return file.delete();
    }

    /**
     * list all files in the (sub-) folder of internal storage
     *
     * @param subfolder
     * @return ArrayList<String> with filenames
     */
    public static ArrayList<String> listFilesInInternalStorage(@NonNull Context context, String subfolder) {
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), "");
        } else {
            file = new File(context.getFilesDir(), subfolder);
            /*
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }

             */

        }
        File[] files = file.listFiles();
        if (files == null) return null;
        ArrayList<String> fileNames = new ArrayList<>();
        for (File value : files) {
            if (value.isFile()) {
                fileNames.add(value.getName());
            }
        }
        return fileNames;
    }

    /**
     * list all files in the (sub-) folder of internal storage without the file extension
     *
     * @param subfolder
     * @param fileExtension
     * @return ArrayList<String> with filenames
     */
    public static ArrayList<String> listFilesInInternalStorage(@NonNull Context context, String subfolder, String fileExtension) {
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), "");
        } else {
            file = new File(context.getFilesDir(), subfolder);
            /*
            if (!subfolderFile.exists()) {
                subfolderFile.mkdirs();
            }

             */

        }
        File[] files = file.listFiles();
        if (files == null) return null;
        ArrayList<String> fileNames = new ArrayList<>();
        for (File value : files) {
            if (value.isFile()) {
                fileNames.add(value.getName().replace(fileExtension, ""));
            }
        }
        return fileNames;
    }

    /**
     * list all folder in the (sub-) folder of internal storage
     *
     * @param subfolder
     * @return ArrayList<String> with folder names
     */
    public static ArrayList<String> listFolderInInternalStorage(@NonNull Context context, String subfolder) {
        File file;
        if (TextUtils.isEmpty(subfolder)) {
            file = new File(context.getFilesDir(), "");
        } else {
            file = new File(context.getFilesDir(), subfolder);
        }
        File[] files = file.listFiles();
        if (files == null) return null;
        ArrayList<String> folderNames = new ArrayList<>();
        for (File value : files) {
            if (!value.isFile()) {
                folderNames.add(value.getName());
            }
        }
        return folderNames;
    }

    /**
     * concatenates the filename with a subfolder
     *
     * @param filename
     * @param subfolder
     * @return a String subfolder | File.separator | filename
     */
    public static String concatenateFilenameWithSubfolder(@NonNull String filename, String subfolder) {
        if (TextUtils.isEmpty(subfolder)) {
            return filename;
        } else {
            return subfolder + File.separator + filename;
        }
    }

    /**
     * splits a complete filename in the filename [0] and extension [1]
     *
     * @param filename with extension
     * @return a String array with filename [0] and extension [1]
     */
    private String[] splitFilename(@NonNull String filename) {
        return filename.split(".");
    }

    /**
     * counts the number of file extensions (testing on '.' in the filename)
     *
     * @param filename
     * @return number of extensions
     */
    //public int countChar(String str, char c)
    private static int getNumberOfExtensions(@NonNull String filename) {
        char c = '.';
        int count = 0;
        for (int i = 0; i < filename.length(); i++) {
            if (filename.charAt(i) == c)
                count++;
        }
        return count;
    }

    private static int getNumberOfExtensionsOld(@NonNull String filename) {
        String[] parts = filename.split(".");
        System.out.println("parts: " + parts.length);
        return parts.length - 1;
    }

    /**
     * converts a filename to a Android safe filename
     *
     * @param filename WITHOUT extension
     * @return new filename
     */
    private String getSafeFilename(@NonNull String filename) {
        final int MAX_LENGTH = 127;
        filename = filename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
        int end = Math.min(filename.length(), MAX_LENGTH);
        return filename.substring(0, end);
    }

    /**
     * converts a filename to an Android safe filename
     *
     * @param filename WITHOUT extension
     * @return new filename
     */
    private static String getSafeFilenameWithoutExtension(@NonNull String filename) {
        final int MAX_LENGTH = 127;
        filename = filename.replaceAll("[^a-zA-Z0-9]", "_");
        int end = Math.min(filename.length(), MAX_LENGTH);
        return filename.substring(0, end);
    }


}
