package org.yourorghere;

import javax.media.opengl.GL;
import javax.media.opengl.glu.GLU;

public class ObjetosPista {

    int[] ceros = {0, 0, 0};
    float[] tierra = {155f / 225f, 130f / 225f, 90f / 225f};
    Figuras3D f3d = new Figuras3D();
    float x, y, z;

    int colorSelect = 0;
    int colorCount = 0;

    int i = 0;

    public void Pista(GL gl) {

        f3d.PrismaCuad(gl, tierra, 64, 64, 2);

    }

    public void DefinicionAux(float[] aux) {
        x = aux[0];
        y = aux[1];
        z = aux[2];

    }

    public void Valla(Colisiones c, GL gl, int pasos, char eje, int sentido) {

        int move = 0;

        if (eje == 'X') {
            while (move < pasos) {
                float[] color = AlternaColorValla();
                f3d.PrismaCuad(gl, color, 1, 1, 1);
                float[] aux1 = {x, y, z};
                c.objetosChoque.add(aux1);
                i++;
                gl.glTranslatef(-sentido, 0, 0);
                move++;
                x += -sentido * 1;
                if (colorCount == 4) {
                    colorCount = 0;
                    colorSelect++;
                }
                colorCount++;
            }
        }

        if (eje == 'Y') {
            while (move < pasos) {
                float[] color = AlternaColorValla();
                f3d.PrismaCuad(gl, color, 1, 1, 1);
                float[] aux1 = {x, y, z};
                c.objetosChoque.add(aux1);
                i++;
                gl.glTranslatef(0, 0, sentido);
                z += sentido * 1;
                move++;

                if (colorCount == 4) {
                    colorCount = 0;
                    colorSelect++;
                }
                colorCount++;
            }
        }

    }

    public float[] AlternaColorValla() {

        float[] color = new float[3];
        int aux = colorSelect % 4;

        switch (aux) {
            case 0:
                color[0] = 1;
                color[1] = 0;
                color[2] = 0;
                break;
            case 1:
                color[0] = 0;
                color[1] = 0;
                color[2] = 1;
                break;
            case 2:
                color[0] = 1;
                color[1] = 1;
                color[2] = 0;
                break;
            case 3:
                color[0] = 0;
                color[1] = 0.75f;
                color[2] = 0;
                break;
        }
        return color;
    }

//    public void ImpresionOP(Colisiones c) {
//        System.out.println("OBJETOS PISTA");
//        for (int i = 0; i < c.objetosChoque.size(); i++) {
//            System.out.println(i);
//            float[] ayuda = c.objetosChoque.get(i);
//            System.out.print(ayuda[0] + "\t");
//            System.out.print(ayuda[1] + "\t");
//            System.out.println(ayuda[2]);
//        }
//    }  
}
