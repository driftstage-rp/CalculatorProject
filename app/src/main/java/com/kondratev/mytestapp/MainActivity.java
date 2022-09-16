package com.kondratev.mytestapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // объявляем переменные здесь, чтобы они были видны во всех методах класса
    EditText etNum1, etNum2, etOperation;
    TextView tvResultText;
    Toast toastError;
    String task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // находим элементы
        etNum1 = (EditText) findViewById(R.id.editTextNum1);
        etNum2 = (EditText) findViewById(R.id.editTextNum2);
        etOperation =(EditText) findViewById(R.id.editTextOperation);
        tvResultText =(TextView)findViewById(R.id.textViewResult);

        Button calculate=(Button)findViewById(R.id.button);
        // прописываем обработчик
        calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        // TODO Auto-generated method stub
        float num1 = 0;  // регистр в имени переменных важен!
        float num2 = 0;
        float result = 0;
        // Проверяем поля на пустоту
        if (TextUtils.isEmpty(etNum1.getText().toString())
                || TextUtils.isEmpty(etNum2.getText().toString())) {
//            if(toastError!=null) //Отлавливаем множественные клики на кнопку останавливаем сообщение,
//                toastError.cancel();    //вызванное предыдущим кликом по кнопке, если оно в текущий момент отображается на экране
//            toastError = Toast.makeText(this, "Вы не заполнили поля операндов", Toast.LENGTH_SHORT);
//            toastError.show();
            return;
        }

        // читаем EditText и заполняем переменные числами
        try {
            num1 = Float.parseFloat(etNum1.getText().toString());
        } catch(NumberFormatException e) {
            if(toastError!=null) //Отлавливаем множественные клики на кнопку останавливаем сообщение,
                toastError.cancel();    //вызванное предыдущим кликом по кнопке, если оно в текущий момент отображается на экране
            toastError = Toast.makeText(this, "Неправильно введен операнд 1", Toast.LENGTH_SHORT);
            toastError.show();
            return;
        }
        try {
            num2 = Float.parseFloat(etNum2.getText().toString());
        }
        catch(NumberFormatException e) {
            if(toastError!=null) //Отлавливаем множественные клики на кнопку останавливаем сообщение,
                toastError.cancel();    //вызванное предыдущим кликом по кнопке, если оно в текущий момент отображается на экране
            toastError = Toast.makeText(this, "Неправильно введен операнд 2", Toast.LENGTH_SHORT);
            toastError.show();
            return;
        }




        // определяем нажатую кнопку и выполняем соответствующую операцию
        // в oper пишем операцию, потом будем использовать в выводе

        boolean flag=true;
        task = etOperation.getText().toString();

        try {
            switch (task) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if(num2 == 0) throw new ArithmeticException();
                    result = num1 / num2;
                    break;
                default:
                    flag=false;
                    break;
            }
        } catch (ArithmeticException e) {
            if(toastError!=null) //Отлавливаем множественные клики на кнопку останавливаем сообщение,
                toastError.cancel();    //вызванное предыдущим кликом по кнопке, если оно в текущий момент отображается на экране
            toastError = Toast.makeText(this, "Нельзя делить на ноль", Toast.LENGTH_SHORT);
            toastError.show();
            return;
        }
        if(flag) {
            // формируем строку вывода
            tvResultText.setText(num1 + " " + task + " " + num2 + " = " + result);
        }
        else {
            if(toastError!=null) //Отлавливаем множественные клики на кнопку останавливаем сообщение,
                toastError.cancel();    //вызванное предыдущим кликом по кнопке, если оно в текущий момент отображается на экране
            toastError = Toast.makeText(this, "Неправильная операция", Toast.LENGTH_SHORT);
            toastError.show();
            tvResultText.setText("Доступные операции: + - * /");
        }

    }
}

/*
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/
