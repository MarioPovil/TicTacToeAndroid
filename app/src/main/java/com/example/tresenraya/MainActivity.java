package com.example.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView Win;
    Integer[] botones;
    int[] tableroInit = new int[]{
            0, 0, 0,
            0, 0, 0,
            0, 0, 0
    };
    int[] tablero = tableroInit;
    int estado = 0;
    int fichasPuestas = 0;
    int turno = 1;
    int[] posGanadora= new int[]{-1,-1,-1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Win = (TextView) findViewById(R.id.WinOrNot);
        Win.setVisibility(View.INVISIBLE);
        botones = new Integer[]{
                R.id.b1, R.id.b2, R.id.b3,
                R.id.b4, R.id.b5, R.id.b6,
                R.id.b7, R.id.b8, R.id.b9
        };
    }

    public void ponerFicha(View view) {
        if (estado == 0) {
            int numBtn = Arrays.asList(botones).indexOf(view.getId());
            if (tablero[numBtn] == 0) {
                turno = 1;
                view.setBackgroundResource(R.drawable.circulo);
                tablero[numBtn] = 1;
                fichasPuestas++;
                estado = comEstado();
                terminarPartida();
                if (estado == 0) {
                    turno = -1;
                    IA();
                    fichasPuestas++;
                    estado = comEstado();
                    terminarPartida();
                }
            }
        }
    }

    public void IA() {
        Random ran = new Random();
        int pos = ran.nextInt(tablero.length);
        while (tablero[pos] != 0) {
            pos = ran.nextInt(tablero.length);
        }
        Button b = (Button) findViewById(botones[pos]);
        b.setBackgroundResource(R.drawable.cruz);
        tablero[pos] = -1;
    }
    public void terminarPartida(){
        int fichaVictoria = R.drawable.circulowin;
        if(estado==1||estado ==-1){
            if(estado==1){
                Win.setTextColor(Color.GREEN);
                Win.setVisibility(View.VISIBLE);
            }else{
                Win.setTextColor(Color.RED);
                Win.setVisibility(View.VISIBLE);
                Win.setText("Has Perdio' :´( ");
                fichaVictoria=R.drawable.cruzwin;
            }
            for(int i = 0; i<posGanadora.length; i++){
                Button b = findViewById(botones[posGanadora[i]]);
                b.setBackgroundResource(fichaVictoria);
            }
        }else if(estado==2){
            Win.setTextColor(Color.BLUE);
            Win.setVisibility(View.VISIBLE);
            Win.setText("Empatao' :0 ");
        }
    }
    public int comEstado() {
        int nuevoEstado = 0;
        if (Math.abs(tablero[0] + tablero[1] + tablero[2]) == 3) {
            posGanadora = new int[]{0, 1, 2};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[3] + tablero[4] + tablero[5]) == 3){
            posGanadora = new int[]{3,4, 5};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[6] + tablero[7] + tablero[8]) == 3){
            posGanadora = new int[]{6, 7, 8};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[0] + tablero[4] + tablero[8]) == 3){
            posGanadora = new int[]{0, 4, 8};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[6] + tablero[4] + tablero[2]) == 3){
            posGanadora = new int[]{6, 4, 2};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[2] + tablero[8] + tablero[5]) == 3){
            posGanadora = new int[]{2, 5, 8};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[1] + tablero[4] + tablero[7]) == 3){
            posGanadora = new int[]{1, 4, 7};
            nuevoEstado = 1 * turno;
        }else if (Math.abs(tablero[0] + tablero[3] + tablero[6]) == 3){
            posGanadora = new int[]{0, 3, 6};
            nuevoEstado = 1 * turno;
        }else if(fichasPuestas==9){
            nuevoEstado=2;
        }

        return nuevoEstado;
    }
}