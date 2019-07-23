package org.yourorghere;

import com.sun.opengl.util.Animator;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import com.sun.opengl.util.GLUT;
import java.awt.MouseInfo;
import javax.media.opengl.GLCapabilities;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PistaMarioKart extends JFrame implements KeyListener {
    
    float tiempo = 0;
    int a;

    boolean botonCVerif = false;
    boolean botonXVerif = false;
    boolean botonLVerif = false;
    boolean botonRVerif = false;

    float[] inicioAuto = {27, 2.0001f, 0};
    float[] vista = {28, 10, -1};

    public ModeloKart mod = new ModeloKart();

    ObjetosPista object = new ObjetosPista();
    Kart kart = new Kart(inicioAuto, 300, 150);
    Colisiones c = new Colisiones();

    float alturaKart;
    int selectCam = 3;

    static GL gl;
    static GLU glu;
    static GLUT glut;

    float autorotate = 0;
    float move = 0;

    public PistaMarioKart() {

        setTitle("Juego Kart");
        setSize(700, 700);
        setLocation(10, 40);
        
        GraphicListener listener = new GraphicListener();
        GLCanvas canvas = new GLCanvas(new GLCapabilities());
        canvas.addGLEventListener(listener);
        getContentPane().add(canvas);
        Animator animator = new Animator(canvas);
        animator.start();

        addKeyListener(this);

    }

    public static void main(String[] args) {

        PistaMarioKart frame = new PistaMarioKart();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_C) {
            botonCVerif = true;
            botonXVerif = false;
            a = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            botonXVerif = true;
            botonCVerif = false;
            a = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            botonLVerif = true;
            botonRVerif = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            botonRVerif = true;
            botonLVerif = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_1) {
            selectCam = 1;
            System.out.println("Camara #1 activa.");
        }
        if (e.getKeyCode() == KeyEvent.VK_2) {
            selectCam = 2;
            System.out.println("Camara #2 activa.");
        }
        if (e.getKeyCode() == KeyEvent.VK_3) {
            selectCam = 3;
            System.out.println("Camara #3 activa.");
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            botonCVerif = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_X) {
            botonXVerif = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            botonLVerif = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            botonRVerif = false;
        }
    }

    public void MovimientoA() {
        if (botonCVerif) {
            kart.Acelerar();
        }
        if (botonXVerif) {
            kart.Retroceder();
        }
        if (botonLVerif) {
            if (botonXVerif) {
                kart.direccion += 10f * kart.velocidad;
            } else {
                kart.direccion += 7.5f * kart.velocidad;
            }
        }
        if (botonRVerif) {
            if (botonXVerif) {
                kart.direccion -= 10f * kart.velocidad;
            } else {
                kart.direccion -= 8f * kart.velocidad;
            }
        }
        kart.RestablecerAceleracion();
    }

    public class GraphicListener implements GLEventListener {

        public void init(GLAutoDrawable drawable) {

            GL gl = drawable.getGL();
            System.err.println("INIT GL IS: " + gl.getClass().getName());

            // Enable VSync
            gl.setSwapInterval(1);

            gl.glClearColor(0.1f, 0.75f, 1, 0);
            gl.glShadeModel(GL.GL_SMOOTH);

            gl.glEnable(GL.GL_DEPTH_TEST);

        }

        public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

            GL gl = drawable.getGL();
            GLU glu = new GLU();

            if (height <= 0) { // avoid a divide by zero error!

                height = 1;
            }
            final float h = (float) width / (float) height;
            gl.glViewport(0, 0, width, height);
            gl.glMatrixMode(GL.GL_PROJECTION);
            gl.glLoadIdentity();
            glu.gluPerspective(45.0f, h, 0.001f, 100);
            gl.glMatrixMode(GL.GL_MODELVIEW);
            gl.glLoadIdentity();

        }

        public void display(GLAutoDrawable drawable) {

            GL gl = drawable.getGL();
            GLU glu = new GLU();
            GLUT glut = new GLUT();

            gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
            gl.glLoadIdentity();

            gl.glScalef(.025f, .025f, .025f);

            switch (selectCam) {
                case 1:
                    glu.gluLookAt(kart.posicion[0] - 10 * Math.cos(Math.toRadians(kart.direccion)), kart.posicion[1] + 3, kart.posicion[2] + 10 * Math.sin(Math.toRadians(kart.direccion)),
                            kart.posicion[0], kart.posicion[1], kart.posicion[2],
                            0, 1, 0);
                    break;
                case 2:
                    glu.gluLookAt(kart.posicion[0] + 0.5f * Math.cos(Math.toRadians(kart.direccion)), kart.posicion[1] + 2, kart.posicion[2] + 0.5f * Math.sin(Math.toRadians(kart.direccion)),
                            kart.posicion[0] + 100 * Math.cos(Math.toRadians(kart.direccion)), 0, kart.posicion[2] - 100 * Math.sin(Math.toRadians(kart.direccion)),
                            0, 1, 0);
                    break;
                case 3:
                    glu.gluLookAt(0, 100, 0, 0, 0, 0, 0, 0, 1);
                    break;
            }

            gl.glPushMatrix();

            object.Pista(gl);

            gl.glBegin(GL.GL_QUADS);

            gl.glColor3f(0.25f, 0.95f, 0.25f);

            gl.glVertex3f(2, 2.005f, 32);
            gl.glVertex3f(-1, 2.005f, 32);
            gl.glVertex3f(-1, 2.005f, 23);
            gl.glVertex3f(2, 2.005f, 23);

            gl.glVertex3f(22, 2.005f, -11);
            gl.glVertex3f(22, 2.005f, 20);
            gl.glVertex3f(16, 2.005f, 20);
            gl.glVertex3f(16, 2.005f, -11);

            gl.glVertex3f(19, 2.005f, 20);
            gl.glVertex3f(19, 2.005f, 22);
            gl.glVertex3f(16, 2.005f, 22);
            gl.glVertex3f(16, 2.005f, 20);

            gl.glVertex3f(16, 2.005f, 13);
            gl.glVertex3f(-7, 2.005f, 13);
            gl.glVertex3f(-7, 2.005f, 12);
            gl.glVertex3f(16, 2.005f, 12);

            gl.glVertex3f(-6, 2.005f, 15);
            gl.glVertex3f(-14, 2.005f, 15);
            gl.glVertex3f(-14, 2.005f, 14);
            gl.glVertex3f(-6, 2.005f, 14);

            gl.glVertex3f(-13, 2.005f, 21);
            gl.glVertex3f(-20, 2.005f, 21);
            gl.glVertex3f(-20, 2.005f, 16);
            gl.glVertex3f(-13, 2.005f, 16);

            gl.glVertex3f(-14, 2.005f, -9);
            gl.glVertex3f(-22, 2.005f, -9);
            gl.glVertex3f(-22, 2.005f, -15);
            gl.glVertex3f(-14, 2.005f, -15);

            gl.glVertex3f(-14, 2.005f, -9);
            gl.glVertex3f(-14, 2.005f, -11);
            gl.glVertex3f(16, 2.005f, -11);
            gl.glVertex3f(16, 2.005f, -9);

            gl.glVertex3f(11, 2.005f, -11);
            gl.glVertex3f(11, 2.005f, -17);
            gl.glVertex3f(19, 2.005f, -17);
            gl.glVertex3f(19, 2.005f, -11);

            gl.glVertex3f(-32, 2.005f, 6);
            gl.glVertex3f(-25, 2.005f, 6);
            gl.glVertex3f(-25, 2.005f, 2);
            gl.glVertex3f(-32, 2.005f, 2);

            gl.glVertex3f(-25, 2.005f, 2);
            gl.glVertex3f(-4, 2.005f, 2);
            gl.glVertex3f(-4, 2.005f, 3);
            gl.glVertex3f(-32, 2.005f, 3);

            gl.glVertex3f(-6, 2.005f, 13);
            gl.glVertex3f(-6, 2.005f, 14);
            gl.glVertex3f(-7, 2.005f, 14);
            gl.glVertex3f(-7, 2.005f, 13);

            gl.glVertex3f(-13, 2.005f, 15);
            gl.glVertex3f(-13, 2.005f, 16);
            gl.glVertex3f(-14, 2.005f, 16);
            gl.glVertex3f(-14, 2.005f, 15);
            
            gl.glVertex3f(100, 2.005f, 100);
            gl.glVertex3f(32, 2.005f, 100);
            gl.glVertex3f(32, 2.005f, -100);
            gl.glVertex3f(100, 2.005f, -100);
            
            gl.glVertex3f(-100, 2.005f, 100);
            gl.glVertex3f(-32, 2.005f, 100);
            gl.glVertex3f(-32, 2.005f, -100);
            gl.glVertex3f(-100, 2.005f, -100);
            
            gl.glVertex3f(32, 2.005f, 100);
            gl.glVertex3f(-32, 2.005f, 100);
            gl.glVertex3f(-32, 2.005f, 32);
            gl.glVertex3f(32, 2.005f, 32);
            
            gl.glVertex3f(32, 2.005f, -100);
            gl.glVertex3f(-32, 2.005f, -100);
            gl.glVertex3f(-32, 2.005f, -32);
            gl.glVertex3f(32, 2.005f, -32);

            gl.glEnd();
            
            gl.glPushMatrix();
            
            float[] aux1 = {21.5f, 2f, 20.5f};
            
            gl.glTranslatef(aux1[0], aux1[1], aux1[2]);
            
            object.DefinicionAux(aux1);
            
            object.Valla(c, gl, 2, 'X', 1);
            object.Valla(c, gl, 2, 'Y', 1);
            object.Valla(c, gl, 4, 'X', 1);
            object.Valla(c, gl, 9, 'Y', -1);
            object.Valla(c, gl, 21, 'X', 1);
            object.Valla(c, gl, 2, 'Y', 1);
            object.Valla(c, gl, 7, 'X', 1);
            object.Valla(c, gl, 6, 'Y', 1);
            object.Valla(c, gl, 8, 'X', 1);
            object.Valla(c, gl, 6, 'Y', -1);
            object.Valla(c, gl, 6, 'X', -1);
            object.Valla(c, gl, 2, 'Y', -1);
            object.Valla(c, gl, 7, 'X', -1);
            object.Valla(c, gl, 2, 'Y', -1);
            object.Valla(c, gl, 23, 'X', -1);
            object.Valla(c, gl, 20, 'Y', -1);
            object.Valla(c, gl, 38, 'X', 1);
            object.Valla(c, gl, 7, 'Y', -1);
            object.Valla(c, gl, 9, 'X', -1);
            object.Valla(c, gl, 4, 'Y', 1);
            object.Valla(c, gl, 24, 'X', -1);
            object.Valla(c, gl, 6, 'Y', -1);
            object.Valla(c, gl, 9, 'X', -1);
            object.Valla(c, gl, 6, 'Y', 1);
            object.Valla(c, gl, 3, 'X', -1);
            object.Valla(c, gl, 33, 'Y', 1);

            gl.glTranslatef(9, 0, 10);
            
            aux1[0] += 10;
            aux1[2] += 11;
            
            object.DefinicionAux(aux1);
            
            object.Valla(c, gl, 63, 'Y', -1);
            object.Valla(c, gl, 63, 'X', 1);
            object.Valla(c, gl, 33, 'Y', 1);
            object.Valla(c, gl, 28, 'X', -1);
            object.Valla(c, gl, 2, 'Y', 1);
            object.Valla(c, gl, 21, 'X', 1);
            object.Valla(c, gl, 3, 'Y', 1);
            object.Valla(c, gl, 7, 'X', 1);
            object.Valla(c, gl, 25, 'Y', 1);
            object.Valla(c, gl, 30, 'X', -1);
            object.Valla(c, gl, 9, 'Y', -1);
            object.Valla(c, gl, 4, 'X', -1);
            object.Valla(c, gl, 9, 'Y', 1);
            object.Valla(c, gl, 29, 'X', -1);

            gl.glPopMatrix();

            gl.glTranslatef(kart.posicion[0], kart.posicion[1], kart.posicion[2]);

            gl.glPushMatrix();

            gl.glRotatef(kart.direccion, 0, 1, 0);

            mod.DibujarAuto(gl, glut, 0.5f);

            gl.glPopMatrix();

            MovimientoA();
            kart.Movimiento(a, c);
            c.objetosChoque.clear();
            object.i = 0;

        }

        public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {

        }

    }

}
