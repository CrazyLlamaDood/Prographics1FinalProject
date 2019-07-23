package org.yourorghere;

import javax.media.opengl.GL;

public class Figuras3D {

    public void PrismaCuad(GL gl, float[] color, float lado1, float lado2, float altura) {

        gl.glColor3f(color[0], color[1], color[2]);

        gl.glBegin(GL.GL_QUADS);

        // Base
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);

        // Lado 1
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);

        // Lado 2
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);

        // Lado 3
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);

        // Lado 4
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);

        // Techo
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, altura, lado2 / 2);

        gl.glEnd();
    }
    
    public void PrismaTri(GL gl, float[] color, float lado1, float lado2, float altura) {

        gl.glColor3f(color[0], color[1], color[2]);

        gl.glBegin(GL.GL_QUADS);

        // Base
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);

        // Frente
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);
        
        // Parte trasera
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);
        
        gl.glEnd();
        
        gl.glBegin(GL.GL_TRIANGLES);

        // Lado 1
        gl.glVertex3f(- lado1 / 2, 0, lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, lado2 / 2);

        // Lado 2
        gl.glVertex3f(- lado1 / 2, 0, - lado2 / 2);
        gl.glVertex3f(- lado1 / 2, altura, - lado2 / 2);
        gl.glVertex3f(lado1 / 2, 0, - lado2 / 2);
        
        gl.glEnd();
    }
}