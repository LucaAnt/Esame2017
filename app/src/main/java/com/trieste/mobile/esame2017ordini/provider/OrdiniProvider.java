package com.trieste.mobile.esame2017ordini.provider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trieste.mobile.esame2017ordini.database.OrdiniDb;
import com.trieste.mobile.esame2017ordini.database.tables.TableHelperOrdini;

public class OrdiniProvider extends ContentProvider {

    public static final String AUTORITY = "com.trieste.mobile.esame2017ordini.provider.OrdiniProvider";

    public static final String BASE_PATH_ORDINI = "ordini";

    public static final int ALL_ORDINI = 1;
    public static final int SINGLE_ORDINE = 0;

    public static final String MIME_TYPE_ORDINI = ContentResolver.CURSOR_DIR_BASE_TYPE + "vnd.all_todos";
    public static final String MIME_TYPE_ORDINE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "vnd.single_todo";

    public static final Uri ORDINI_PROVIDER_URI = Uri.parse(ContentResolver.SCHEME_CONTENT + "://" + AUTORITY
            + "/" + BASE_PATH_ORDINI);

    private OrdiniDb database;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTORITY, BASE_PATH_ORDINI, ALL_ORDINI);
        uriMatcher.addURI(AUTORITY, BASE_PATH_ORDINI + "/#", SINGLE_ORDINE);
    }


    @Override
    public boolean onCreate() {
        database = new OrdiniDb(getContext());
        return true;

    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = database.getReadableDatabase();
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ORDINE:
                builder.setTables(TableHelperOrdini.TABLE_NAME);
                builder.appendWhere(TableHelperOrdini._ID + " = " + uri.getLastPathSegment());
                break;
            case ALL_ORDINI:
                builder.setTables(TableHelperOrdini.TABLE_NAME);
                break;
        }
        Cursor cursor = builder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case SINGLE_ORDINE:
                return MIME_TYPE_ORDINE;
            case ALL_ORDINI:
                return MIME_TYPE_ORDINI;

        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        if (uriMatcher.match(uri) == ALL_ORDINI) {
            SQLiteDatabase db = database.getWritableDatabase();
            long result = db.insert(TableHelperOrdini.TABLE_NAME, null, values);
            String resultString = ContentResolver.SCHEME_CONTENT + "://" + BASE_PATH_ORDINI + "/" + result;
            getContext().getContentResolver().notifyChange(uri, null);
            return Uri.parse(resultString);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ORDINE:
                table = TableHelperOrdini.TABLE_NAME;
                query = TableHelperOrdini._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_ORDINI:
                table = TableHelperOrdini.TABLE_NAME;
                query = selection;
                break;
        }
        int deletedRows = db.delete(table, query, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return deletedRows;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String table = "", query = "";
        SQLiteDatabase db = database.getWritableDatabase();
        switch (uriMatcher.match(uri)) {
            case SINGLE_ORDINE:
                table = TableHelperOrdini.TABLE_NAME;
                query = TableHelperOrdini._ID + " = " + uri.getLastPathSegment();
                if (selection != null) {
                    query += " AND " + selection;
                }
                break;
            case ALL_ORDINI:
                table = TableHelperOrdini.TABLE_NAME;
                query = selection;
                break;
        }
        int updatedRows = db.update(table, values, query, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);

        return updatedRows;
    }
}
