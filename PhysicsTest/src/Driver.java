import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;

public class Driver extends BasicGame
{
	public static final int WIDTH = 800, HEIGHT = 600, FPS = 30;
	Circle sphere;
	Vector2f velocity = new Vector2f(5, 15);
	private boolean paused = true;

	public Driver(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException
	{
		AppGameContainer gc = new AppGameContainer(new Driver(
				"Physics"));
		gc.setDisplayMode(WIDTH, HEIGHT, false);
		gc.setTargetFrameRate(FPS);
		gc.start();
	}

	public void init(GameContainer gc) throws SlickException
	{
		sphere = new Circle(100, HEIGHT / 4, 40);
	}

	public void render(GameContainer gc, Graphics g)
			throws SlickException
	{
		g.fill(sphere);
		g.drawString("Velocity Y: " + velocity.getY(), 400, 0);
		g.drawString("Velocity X: " + velocity.getX(), 400, 15);
		g.drawString("Max y: " + sphere.getMaxY(), 400, 45);

	}

	public void update(GameContainer gc, int delta)
			throws SlickException
	{
		Input key = gc.getInput();
		
		if(key.isKeyPressed(Input.KEY_P))
			paused = !paused;
		if (paused)
		{
		} else
		{
			// Impact
			if (sphere.getMaxY() >= HEIGHT)
			{
				sphere.setLocation(sphere.getX(),
						HEIGHT - sphere.getHeight());
				velocity.y = -velocity.y;

				if (velocity.getX() > 0)
				{
					velocity.x = velocity.getX() - 0.3f;
				} else if (velocity.getX() < 0)
				{
					velocity.x = 0;
				}
			}
			if (velocity.getY() < 10)
			{
				velocity.y -= -2f;
			}
			sphere.setLocation(sphere.getX() + velocity.getX(),
					sphere.getY() + velocity.getY());
		}
	}
}
