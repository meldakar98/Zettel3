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






  int width,height;
  Game game;
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

    gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glLoadIdentity();
    gl.glOrtho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
    gl.glColor3f(1.0f, 1.0f, 1.0f);


    gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

    game.drawAll(gl);
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

  game=new Game();
}


  public void dispose(GLAutoDrawable d) {}

  void updateGameState() {
    // Update left paddle position
    if (leftPaddleUp) {
      // Move left paddle up
      // Add your logic here to update the left paddle position
      game.playe1.moveUp();
    } else if (leftPaddleDown) {
      // Move left paddle down
      // Add your logic here to update the left paddle position
      game.playe1.movedown();
    }

    // Update right paddle position
    if (rightPaddleUp) {
      // Move right paddle up
      // Add your logic here to update the right paddle position
      game.player2.moveUp();
    } else if (rightPaddleDown) {
      // Move right paddle down
      // Add your logic here to update the right paddle position
      game.player2.movedown();
    }
    game.ball.move();
  }
}
class Game{
  final Player playe1;
  final Player player2;
  Ball ball;
  public Game(){

    // Start a game loop (for demonstration purposes)
    playe1=new Player(-0.9f);
    player2=new Player(0.9f);
    ball=new Ball();
  }
  public void drawAll(GL2 gl)
  {
    playe1.drawBar(gl);
    player2.drawBar(gl);
    playe1.drawScore(gl);
    player2.drawScore(gl);
    ball.draw(gl);

  }
  class Ball{

    float[] ballData = { 0.02f, 0.02f, -0.02f, 0.02f, -0.02f, -0.02f, 0.02f, -0.02f };
    float x=0,y=0,deltax=0.01f,deltay=0.01f;
    public void move()

    {
      x+=deltax;
      //y+=deltay;
      if(x>0.85f||x<-0.85f) {
        if (player2.intersects(this)) {

          deltax *= -1;
        } else if (playe1.intersects(this)) {
          deltax *= -1;
        } else {
          if (x > 0.85f)
            player2.score++;
          else playe1.score++;
          deltax *= -1;
        }
      }
      if(y>0.9f||y<-0.9f)
        deltay*=-1;

    }
    public void draw(GL2 gl)
    {

      gl.glLoadIdentity();
      gl.glTranslatef(x, y, 0); // Translate to the right
      gl.glBegin(GL_QUADS);

      for (int i = 0; i < ballData.length-1; i+=2) {

        gl.glVertex3f(ballData[i], ballData[i+1], 0.0f);
      }
      ; // translate
      gl.glEnd();
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

  float[] score3Data =
          { 0.06f, 0.1f, 0.04f, 0.1f, 0.04f, -0.1f, 0.06f,
          -0.1f, 0.05f, 0.1f, 0.05f, 0.08f, -0.05f, 0.08f, -0.05f, 0.1f,
          0.05f, -0.08f, 0.05f, -0.1f, -0.05f, -0.1f, -0.05f, -0.08f, 0.05f,
          0.01f, 0.05f, -0.01f, -0.05f, -0.01f, -0.05f, 0.01f};

  float[] barData = { 0.02f, 0.2f, -0.02f, 0.2f, -0.02f, -0.2f, 0.02f, -0.2f };
  int score=0;
  float x,y;

  float minX=barData[0],minY=barData[1],maxX=barData[0],maxY=barData[1];
  public Player(float x)
  {
    this.x=x;
    for (int i = 0; i < barData.length-1; i+=2) {
      if(minX>barData[i])
        minX=barData[i];
      if(maxX<barData[i])
        maxX=barData[i];
      if(minY>barData[i+1])
        minY=barData[i+1];
      if(maxY<barData[i+1])
        minX=barData[i+1];
    }

    System.out.println(minX+"\n"+ maxX+"\n"+minY+"\n"+ maxY+"\n");

  }
  public void moveUp()
  {
    y+=0.01;
  }
  public void movedown()
  {
    y-=0.01;
  }
  void drawScore(GL2 gl)
  {
    score=score%4;
    float[] data;
    if(score==0)
      data=score0Data;
    else if (score==1)
      data=score1Data;
    else if (score==2)
      data=score2Data;
    else
      data=score3Data;
    float f=-0.1f;
    if(x>0)
    {
      f=0.1f;
    }

    gl.glLoadIdentity();
    gl.glTranslatef(f, 0.9f, 0);
    gl.glBegin(GL_QUADS);

    for (int i = 0; i < data.length-1; i+=2) {

      gl.glVertex3f(data[i], data[i+1], 0.0f);
    }
    ; // translate
    gl.glEnd();
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

  public boolean intersects(Game.Ball ball) {
    if(ball.x>=this.x+minX-0.05&&ball.x<=this.x+maxX+0.05)
    {
      System.out.println("here");
      if(ball.y>=this.y+minY-0.05&&ball.y<=this.y+maxY+0.05)
        return true;
      System.out.println("here too");
    }
    return false;
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
    if(e.getKeyChar()=='9')
    {
      renderer.game.playe1.score++;
      renderer.game.playe1.score=renderer.game.playe1.score%4;
      System.out.println(renderer.game.playe1.score);

    }
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