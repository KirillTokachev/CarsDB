package com.example.carsdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DataBaseHandler;
import Model.Car;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

        Log.d("CarsCount:", String.valueOf(dataBaseHandler.detCarsCount()));

        // Добавление данных в базу данных
        /*dataBaseHandler.addCar(new Car("Toyota","30 000 $"));
        dataBaseHandler.addCar(new Car("Opel","25 000 $"));
        dataBaseHandler.addCar(new Car("Mercedes","50 000 $"));
        dataBaseHandler.addCar(new Car("Volvo","75 000 $"));*/

        List<Car> carsList = dataBaseHandler.getAllCars();

        /*for (Car car : carsList){
            Log.d("Car Info: ","ID " + car.getId() + " name " + car.getName() + " price" + car.getPrice());
        }*/

        // Удаление данных
        /*Car deletingCar = dataBaseHandler.getCar(1);
        dataBaseHandler.deleteCar(deletingCar);*/

        for (Car car : carsList){
            Log.d("Car Info: ","ID " + car.getId() + " name " + car.getName() + " price" + car.getPrice());
        }

       /* Car car = dataBaseHandler.getCar(1);
        Log.d("Car Info: ","ID " + car.getId() + " name " + car.getName() + " price " + car.getPrice());*/

        // Обновление данных
        /*car.setName("Tesla");
        car.setPrice("60 000 $");

        dataBaseHandler.updateCar(car);

        Log.d("Car Info: ","ID " + car.getId() + " name " + car.getName() + " price " + car.getPrice());*/



    }
}