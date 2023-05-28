package com.example.task71p;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
//The DatabaseClass extends SQLiteOpenHelper to handle database creation and version management.
public class DatabaseClass extends SQLiteOpenHelper {

    // Database constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LostFoundDb.db";
    private static final String TABLE_NAME = "lost_found";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_IS_LOST_OR_FOUND = "is_lost_or_found";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_LOCATION = "location";

    public DatabaseClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the database table
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_IS_LOST_OR_FOUND + " TEXT, " +
                COLUMN_PHONE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_LOCATION + " TEXT)";

        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table and recreate it on database upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(DATA DATA) {
        // Insert new data into the database
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, DATA.getName());
        values.put(COLUMN_IS_LOST_OR_FOUND, DATA.getIsLostOrFound());
        values.put(COLUMN_PHONE, DATA.getPhone());
        values.put(COLUMN_DESCRIPTION, DATA.getDescription());
        values.put(COLUMN_DATE, DATA.getDate());
        values.put(COLUMN_LOCATION, DATA.getLocation());

        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, values);
        db.close();

        return id;
    }

    public DATA getDataById(int id) {
        // Retrieve data from the database based on ID
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        DATA DATA = null;
        if (cursor.moveToFirst()) {
            // Retrieve column indices
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int lostOrFoundIndex = cursor.getColumnIndex(COLUMN_IS_LOST_OR_FOUND);
            int phoneIndex = cursor.getColumnIndex(COLUMN_PHONE);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
            int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
            int locationIndex = cursor.getColumnIndex(COLUMN_LOCATION);

            if (idIndex != -1 && nameIndex != -1 && lostOrFoundIndex != -1 &&
                    phoneIndex != -1 && descriptionIndex != -1 && dateIndex != -1 &&
                    locationIndex != -1) {
                // Create a new DATA object with retrieved data
                DATA = new DATA(
                        cursor.getString(lostOrFoundIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(phoneIndex),
                        cursor.getString(descriptionIndex),
                        cursor.getString(dateIndex),
                        cursor.getString(locationIndex),
                        cursor.getInt(idIndex)
                );
            }
        }

        cursor.close();
        db.close();

        return DATA;
    }
    //The getData method retrieves all DATA objects from the database and returns them as an ArrayList.
    public ArrayList<DATA> getData() {
        // Retrieve all data from the database
        ArrayList<DATA> data = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                // Retrieve column indices
                int idIndex = cursor.getColumnIndex(COLUMN_ID);
                int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
                int lostOrFoundIndex = cursor.getColumnIndex(COLUMN_IS_LOST_OR_FOUND);
                int phoneIndex = cursor.getColumnIndex(COLUMN_PHONE);
                int descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION);
                int dateIndex = cursor.getColumnIndex(COLUMN_DATE);
                int locationIndex = cursor.getColumnIndex(COLUMN_LOCATION);

                if (idIndex != -1 && nameIndex != -1 && lostOrFoundIndex != -1 &&
                        phoneIndex != -1 && descriptionIndex != -1 && dateIndex != -1 &&
                        locationIndex != -1) {
                    // Create a new DATA object with retrieved data and add it to the list
                    DATA model = new DATA(
                            cursor.getString(lostOrFoundIndex),
                            cursor.getString(nameIndex),
                            cursor.getString(phoneIndex),
                            cursor.getString(descriptionIndex),
                            cursor.getString(dateIndex),
                            cursor.getString(locationIndex),
                            cursor.getInt(idIndex)
                    );

                    data.add(model);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return data;
    }

    public int deleteDataById(int id) {
        // Delete data from the database based on ID
        SQLiteDatabase db = getWritableDatabase();
        String selection = COLUMN_ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        int deletedRows = db.delete(TABLE_NAME, selection, selectionArgs);
        db.close();

        return deletedRows;
    }
}

