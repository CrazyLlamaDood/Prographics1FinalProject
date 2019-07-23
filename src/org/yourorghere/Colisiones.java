package org.yourorghere;

import java.util.ArrayList;

public class Colisiones {

    public ArrayList<float[]> objetosChoque = new ArrayList<float[]>();
    int ref;

    public boolean VerificarColision(float[] posicion) {

        boolean aux = false;
        for (int i = 0; i < objetosChoque.size(); i++) {
            float[] ayuda = objetosChoque.get(i);
            float[] vector = {ayuda[0] - posicion[0], 0, ayuda[2] - posicion[2]};
            float distancia = (float) Math.sqrt(vector[0] * vector[0] + vector[2] * vector[2]);

            if (distancia <= 1.414213562f) {
                ref = i;
                aux = true;
                break;
            }
        }

        return aux;
    }
    
    public float Distancia(float[] posicion) {
        float[] ayuda = objetosChoque.get(ref);
        float[] vector = {ayuda[0] - posicion[0], 0, ayuda[2] - posicion[2]};
        float distancia = (float) Math.sqrt(vector[0] * vector[0] + vector[2] * vector[2]);
        return distancia;
    }

}
