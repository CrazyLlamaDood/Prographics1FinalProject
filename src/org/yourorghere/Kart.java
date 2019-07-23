package org.yourorghere;

public class Kart {

    public float[] posicion;
    public float direccion = 90;
    public float velocidad;
    public float aceleracion;

    public float topAceleracion;
    public float topVelocidad;

    public Kart(float[] posicion, float topAceleracion, float topVelocidad) {

        this.posicion = posicion;
        this.topAceleracion = topAceleracion;
        this.topVelocidad = topVelocidad;

    }

    public void Movimiento(int a, Colisiones c) {
        float rozamiento = velocidad * 0.1f;
        velocidad += aceleracion - rozamiento;

        boolean colision = c.VerificarColision(posicion);
        if (colision == true) {
            float d = 0.1f * c.Distancia(posicion);
            aceleracion = 0;
            velocidad = 0;
            if (a == 1) {
                posicion[0] += (float) (Math.cos(Math.toRadians(direccion + 180)) * d);
                posicion[2] -= (float) (Math.sin(Math.toRadians(direccion + 180)) * d);
            } else if (a == 0) {
                posicion[0] += (float) (Math.cos(Math.toRadians(direccion)) * d);
                posicion[2] -= (float) (Math.sin(Math.toRadians(direccion)) * d);
            }
        } else {
            posicion[0] += (float) (Math.cos(Math.toRadians(direccion)) * velocidad);
            posicion[2] -= (float) (Math.sin(Math.toRadians(direccion)) * velocidad);
        }

    }

    public void Acelerar() {

        while (aceleracion < 0.05f) {
            if (aceleracion >= 0) {
                aceleracion += 0.00001f;
            } else {
                aceleracion += 0.00005f;
            }
        }

    }

    public void Retroceder() {

        while (aceleracion > -0.025f) {
            if (aceleracion <= 0) {
                aceleracion -= 0.01f;
            } else {
                aceleracion -= 0.001f;
            }
        }
    }

    public void RestablecerAceleracion() {
        if (aceleracion != 0) {
            if (aceleracion > 0) {
                aceleracion -= 0.01f;
            } else {
                aceleracion += 0.01f;
            }
            if (Math.abs(aceleracion) < 0.01f) {
                aceleracion = 0;
            }
        }
    }

}
