import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame{
	Asteriods game= new Asteriods();
		
    public Main() {
		super("Asteriods");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    game.setPos();
		add(game);
    game.repaint();
   
		pack();  // set the size of my Frame exactly big enough to hold the contents
		setVisible(true);
    }
    
    public static void main(String[] arguments) {
		Main frame = new Main();		
    }
}


class Asteriods extends JPanel implements KeyListener, ActionListener, MouseListener{
	private int boxx, boxy;
	private int ballx=-1, bally=-1;
	public static final int INTRO=0, GAME=1, END=2;
	private int screen = INTRO;
  private int astX;
  private int astY;

  private Point mouse;

  private boolean playButtonHover = false;
  private boolean highscoreButtonHover = false;
  private boolean badAstpaint = false;

  //playButton = new Rectangle(drawRect(230,335,250,30));
  //highscoreButton = new Rectangle(drawRect(215,390,285,30));
	
	private boolean []keys;
	Timer timer;
	Image back;
  Image badAst1,badAst2,badAst3;
	
	public Asteriods(){
		back = new ImageIcon("Asteriods_intro.png").getImage();
    badAst1 = new ImageIcon("badAsteriods1.png").getImage();
    // badAst2 = new ImageIcon("badAsteriods2.png").getImage();
    // badAst3 = new ImageIcon("badAsteriods3.png").getImage();
		keys = new boolean[KeyEvent.KEY_LAST+1];
		boxx = 400;
		boxy = 300;
    

		setPreferredSize(new Dimension(700, 500));
		timer = new Timer(10, this);
		timer.start();
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		addMouseListener(this);
	}
  
  public void setPos(){
      astX=30;
      astY=30;
  }
  public void update(){
		if(screen == GAME){
			move();
		}
		mouse = MouseInfo.getPointerInfo().getLocation();
		Point offset = getLocationOnScreen();
		mouse.translate(-offset.x, -offset.y);
	}

  static int randint(int low, int high){
		return (int)(Math.random()*(high-low+1)+low);
	}

	public void move(){
		if(screen == INTRO){
		}
		else if(screen == GAME){
			if(keys[KeyEvent.VK_LEFT]){
				boxx -= 5;
			}		
			if(keys[KeyEvent.VK_RIGHT]){
				boxx += 5;
			}		
			if(keys[KeyEvent.VK_UP]){
				boxy -= 5;
			}		
			if(keys[KeyEvent.VK_DOWN]){
				boxy += 5;
			}
		}
	}

  public void moveAst(Graphics g){
    astX = randint(0,100);
    astY = randint(0,100);
    badAstpaint = true;
  }

	
	@Override
	public void actionPerformed(ActionEvent e){
		move();
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent ke){
		int key = ke.getKeyCode();
		keys[key] = false;
	}	
	
	@Override
	public void keyPressed(KeyEvent ke){
		int key = ke.getKeyCode();
		keys[key] = true;
	}
	
	@Override
	public void keyTyped(KeyEvent ke){

  }
	@Override
	public void	mouseClicked(MouseEvent e){

  }

	@Override
	public void	mouseEntered(MouseEvent e){
    if(screen == INTRO){
      if(e.getX() > 230 && e.getX() < 480 &&
			   e.getY() > 335 && e.getY() < 365){
          playButtonHover = true;
			   }
      if(e.getX() > 215 && e.getX() < 500 &&
			   e.getY() > 390 && e.getY() < 420){
           highscoreButtonHover = true;
         }	
      else{
        playButtonHover = false;
        highscoreButtonHover = false;
      }	
    }
  }

	@Override
	public void	mouseExited(MouseEvent e){
  }

	@Override
	public void	mousePressed(MouseEvent e){
		if(screen == INTRO){
			if(e.getX() > 230 && e.getX() < 480 &&
			   e.getY() > 335 && e.getY() < 365){
           screen = GAME;
		  }
		  else if(screen == GAME){
			ballx = e.getX();
			bally = e.getY();
		  }	
    }
	}

	@Override
	public void	mouseReleased(MouseEvent e){}

   public void move(int dx, int dy){
      astX += dx;
      astY += dy;
    }


	@Override
	public void paint(Graphics g) {
		if(screen == INTRO){
			g.drawImage(back,0,0,this);	

      g.setColor(Color.WHITE);
      g.drawRect(230,335,250,30);
      g.drawRect(215,390,285,30); 

      if (playButtonHover = true){
        g.setColor(Color.RED);
        g.drawRect(230,335,250,30);
      }
      if (highscoreButtonHover = true){
        g.setColor(Color.RED);
        g.drawRect(215,390,285,30); 
      }
		}

		else if(screen == GAME){
			g.setColor(Color.BLACK);
			g.fillRect(0,0,800,800);
        while (astY < 800) {
          g.drawImage(badAst1, astX, astY, this);
          astY += badAst1.getHeight(null);
          astX += badAst1.getWidth(null); 
          System.out.println(astX + "" +astY); 
        }
		  }
    }

}