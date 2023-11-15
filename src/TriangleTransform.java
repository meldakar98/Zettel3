// This code example is created for educational purpose
// by Thorsten Thormaehlen (contact: www.thormae.de).
// It is distributed without any warranty.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import static com.jogamp.opengl.GL.*;  
import static com.jogamp.opengl.GL2.*; 

class Renderer {
  public boolean rightPaddleUp;
  public boolean leftPaddleDown;
  public boolean leftPaddleUp;
  public boolean rightPaddleDown;

  float[] ballData = { 0.02f, 0.02f, -0.02f, 0.02f, -0.02f, -0.02f, 0.02f, -0.02f };



Player playe1,player2;


  int width,height;
  public void init(GLAutoDrawable d) {

  }
  public void resize(GLAutoDrawable d, int width, int height) {
    GL2 gl = d.getGL().getGL2(); // get the OpenGL 2 graphics context
    this.width=width;
    this.height=height;
    gl.glViewport(0, 0, width, height);
  }
  public void display(GLAutoDrawable d) {
    GL2 gl = d.getGL().getGL2();  // get the OpenGL 2 graphics context
/*
    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glLoadIdentity();
    gl.glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
    gl.glColor3f(1.0f, 1.0f, 1.0f);
    drawzero(gl);
    drawBarPlayer1(gl);
   drawBarPlayer2(gl);

*/
    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    // Draw the shape on the right
    playe1.drawBar(gl);

    // Draw the shape on the left
    player2.drawBar(gl);

/*
    gl.glColor3f(0.0f, 0.0f, 1.0f);
    gl.glScalef(0.5f, 0.5f, 0.0f); // resize to 50%
    drawTriangle(gl);

    gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glRotatef(45.0f, 0.0f, 0.0f, 1.0f); //rotate by 45 degrees
    drawTriangle(gl);

    gl.glColor3f(0.0f, 1.0f, 0.0f);
    gl.glTranslatef(0.5f, 0.5f, 0.0f); // translate
    drawTriangle(gl);

 */
  }

public Renderer(){

  // Start a game loop (for demonstration purposes)
playe1=new Player(-0.9f);
player2=new Player(0.9f);
}


  public void dispose(GLAutoDrawable d) {}

  void updateGameState() {
    // Update left paddle position
    if (leftPaddleUp) {
      // Move left paddle up
      // Add your logic here to update the left paddle position
      playe1.moveUp();
    } else if (leftPaddleDown) {
      // Move left paddle down
      // Add your logic here to update the left paddle position
      playe1.movedown();
    }

    // Update right paddle position
    if (rightPaddleUp) {
      // Move right paddle up
      // Add your logic here to update the right paddle position
    player2.moveUp();
    } else if (rightPaddleDown) {
      // Move right paddle down
      // Add your logic here to update the right paddle position
      player2.movedown();
    }
  }
}
class Player{

  float[] score0Data = { 0.06f, 0.1f, 0.04f, 0.1f, 0.04f, -0.1f, 0.06f,
          -0.1f, -0.04f, 0.1f, -0.06f, 0.1f, -0.06f, -0.1f, -0.04f, -0.1f,
          0.05f, 0.1f, 0.05f, 0.08f, -0.05f, 0.08f, -0.05f, 0.1f, 0.05f,
          -0.08f, 0.05f, -0.1f, -0.05f, -0.1f, -0.05f, -0.08f };

  float[] score1Data = { 0.01f, 0.1f, -0.01f, 0.1f, -0.01f, -0.1f, 0.01f,
          -0.1f };

  float[] score2Data = { 0.06f, 0.1f, 0.04f, 0.1f, 0.04f, 0.0f,
          0.06f, 0.0f, -0.04f, 0.0f, -0.06f, 0.0f,
          -0.06f, -0.1f, -0.04f, -0.1f, 0.05f, 0.1f, 0.05f,
          0.08f, -0.05f, 0.08f, -0.05f, 0.1f, 0.05f, -0.08f, 0.05f,
          -0.1f, -0.05f, -0.1f, -0.05f, -0.08f, 0.05f,
          0.01f, 0.05f, -0.01f, -0.05f, -0.01f, -0.05f,
          0.01f };

  float[] score3Data = { 0.06f, 0.1f, 0.04f, 0.1f, 0.04f, -0.1f, 0.06f,
          -0.1f, 0.05f, 0.1f, 0.05f, 0.08f, -0.05f, 0.08f, -0.05f, 0.1f,
          0.05f, -0.08f, 0.05f, -0.1f, -0.05f, -0.1f, -0.05f, -0.08f, 0.05f,
          0.01f, 0.05f, -0.01f, -0.05f, -0.01f, -0.05f, 0.01f};

  float[] barData = { 0.02f, 0.2f, -0.02f, 0.2f, -0.02f, -0.2f, 0.02f, -0.2f };
  float x,y;
  public Player(float x)
  {
    this.x=x;
  }
  public void moveUp()
  {
    y+=0.01;
  }
  public void movedown()
  {
    y-=0.01;
  }
  public void drawBar(GL2 gl)
  {

    gl.glLoadIdentity();
    gl.glTranslatef(x, 0, 0); // Translate to the right
    gl.glBegin(GL_QUADS);

    for (int i = 0; i < barData.length-1; i+=2) {

      gl.glVertex3f(barData[i], barData[i+1]+y, 0.0f);
    }
    ; // translate
    gl.glEnd();
  }
}

class MyGui extends JFrame implements GLEventListener,KeyListener {

  private Renderer renderer;
  
  public void createGUI() {
    setTitle("TriangleTransform");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
    GLProfile glp = GLProfile.getDefault();
    GLCapabilities caps = new GLCapabilities(glp);
    GLCanvas canvas = new GLCanvas(caps);
    setSize(320, 320);
    getContentPane().add(canvas);
    final FPSAnimator ani = new FPSAnimator(canvas, 60, true);
    canvas.addGLEventListener(this);
    canvas.addKeyListener(this);
    setVisible(true);
    renderer = new Renderer();

    ani.start();
    Timer timer = new Timer(10, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Update the game state based on paddle movement
        renderer.updateGameState();
      }
    });
    timer.start();

  }


  @Override
  public void init(GLAutoDrawable d) { 
    renderer.init(d); 
  }

  @Override
  public void reshape(GLAutoDrawable d, int x, int y, int width, int height) {
    renderer.resize(d, width, height);
  }

  @Override
  public void display(GLAutoDrawable d) {
    renderer.display(d);
  }

  @Override
  public void dispose(GLAutoDrawable d) { 
    renderer.dispose(d);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }
  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();

    // Check which key is pressed and set the corresponding flag to true
    switch (keyCode) {
      case KeyEvent.VK_W:
        renderer.leftPaddleUp = true;
        break;
      case KeyEvent.VK_S:
        renderer.leftPaddleDown = true;
        break;
      case KeyEvent.VK_P:
        renderer.rightPaddleUp = true;
        break;
      case KeyEvent.VK_L:
        renderer.rightPaddleDown = true;
        break;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();

    // Check which key is released and set the corresponding flag to false
    switch (keyCode) {
      case KeyEvent.VK_W:
        renderer.leftPaddleUp = false;
        break;
      case KeyEvent.VK_S:
        renderer.leftPaddleDown = false;
        break;
      case KeyEvent.VK_P:
        renderer.rightPaddleUp = false;
        break;
      case KeyEvent.VK_L:
        renderer.rightPaddleDown = false;
        break;
    }
  }
}

public class TriangleTransform {
  public static void main(String[] args) {
    System.setProperty("sun.java2d.uiScale", "1.0");
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        MyGui myGUI = new MyGui();
        myGUI.createGUI();
      }
    });
  }
}