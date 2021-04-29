package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import Model.Car;
import utils.Util;

public class DataBaseHandler extends SQLiteOpenHelper {

    public DataBaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL
        // Создание зоголовков полей таблицы
        String CREATE_CARS_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_NAME + " TEXT,"
                + Util.KEY_PRICE + " TEXT" + ")";

        // Создание самой таблицы
        db.execSQL(CREATE_CARS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);
        onCreate(db);
    }

    //CRUD
    // Create, Read, Update, Delete

    //Добавление данных в базу
    public void addCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
    }

    // Метод извлечения данных
    public Car getCar(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Util.TABLE_NAME, new String[] {Util.KEY_ID, Util.KEY_NAME, Util.KEY_PRICE},
                Util.KEY_ID + "=?", new String[] {String.valueOf(id)},
                null, null,
                null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }

        Car car = new Car(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

        return car;
    }


    public List<Car> getAllCars(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Car> carsList = new ArrayList<>();

        String selectAllCars = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAllCars,null);
        if (cursor.moveToFirst()){
            do {
                Car car = new Car();
                car.setId(Integer.parseInt(cursor.getString(0)));
                car.setName(cursor.getString(1));
                car.setPrice(cursor.getString(2));

                carsList.add(car);
            }while (cursor.moveToNext());
        }

        return carsList;
    }

    // Обновление данных
    public int updateCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.KEY_NAME, car.getName());
        contentValues.put(Util.KEY_PRICE, car.getPrice());

        return db.update(Util.TABLE_NAME, contentValues, Util.KEY_ID + "=?",
               new String[] {String.valueOf(car.getId())});
    }

    // Удаление данных
    public void deleteCar(Car car){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME, Util.KEY_ID + "=?",
                new String[] {String.valueOf(car.getId())});

        db.close();
    }

    // Метод возвращения всех записей находящихся в таблице
    public int detCarsCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
