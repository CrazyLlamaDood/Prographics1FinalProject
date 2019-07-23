package org.yourorghere;

import com.sun.opengl.util.Animator;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.GLUT;
import javax.media.opengl.GLCapabilities;

public class ModeloKart {

    Figuras3D f3d = new Figuras3D();

    public void Llantas(GLUT glut, GL gl, float diametro, float ancho) {

        glut = new GLUT();

        gl.glColor3f(0.15f, 0.15f, 0.15f);
        glut.glutSolidCylinder(diametro / 2, ancho, 20, 1);

        gl.glColor3f(1, 1, 1);
        gl.glTranslatef(0, 0, ancho);
        glut.glutSolidCylinder(diametro / 4, 0.01f, 20, 1);

    }

    public void DibujarAuto(GL gl, GLUT glut, float escala) {

        gl.glScalef(escala, escala, escala);

        float[] rojo = {1, 0, 0};
        float[] rojoOsc = {0.5f, 0, 0};
        float[] gris = {0.5f, 0.5f, 0.5f};
        float[] grisOsc = {0.15f, 0.15f, 0.15f};

        gl.glTranslatef(0, 0.25f, 0);

        f3d.PrismaCuad(gl, rojoOsc, 3, 2.5f, 0.5f);

        gl.glTranslatef(0.875f, 0.5f, 0f);

        f3d.PrismaTri(gl, rojo, 1.25f, 1.5f, 1.5f);

        gl.glTranslatef(-0.25f, -0.25f, 0.75f);

        Llantas(glut, gl, 1, 0.75f);

        gl.glTranslatef(-2, 0, -0.75f);

        Llantas(glut, gl, 1, 0.75f);

        gl.glRotatef(180, 1, 0, 0);
        gl.glTranslatef(0, 0, 2.25f);

        Llantas(glut, gl, 1, 0.75f);

        gl.glTranslatef(2, 0, -0.75f);

        Llantas(glut, gl, 1, 0.75f);

        gl.glRotatef(180, 1, 0, 0);
        gl.glTranslatef(-1f, 0.25f, 1.5f);

        f3d.PrismaCuad(gl, gris, 1.5f, 1, 0.25f);

        gl.glTranslatef(0, 0.25f, 0f);

        f3d.PrismaCuad(gl, grisOsc, 1.5f, 1f, 0.25f);

        gl.glTranslatef(-0.5f, 0.75f, 0f);
        gl.glRotatef(90, 0, 0, 1);

        f3d.PrismaCuad(gl, grisOsc, 1.5f, 1f, 0.25f);

        gl.glRotatef(-90, 1, 0, 0);
        gl.glTranslatef(-1, -0.625f, -1.5f);
        gl.glColor3f(0.5f, 0.5f, 0.5f);

        glut.glutSolidCylinder(0.125f, 2.5f, 20, 8);

        gl.glTranslatef(0, 1.25f, 0);
        gl.glColor3f(0.5f, 0.5f, 0.5f);

        glut.glutSolidCylinder(-0.125f, 2.5f, 20, 8);
        
    }
}
