package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;

public class Player {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;
    private boolean boosting;
    private boolean isShooting;
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    private Rect detectCollision;
    private ArrayList<Bullet> bullets;
    private long lastShootTime;
    private final int SHOOT_DELAY = 500;

    public Player(Context context, int screenX, int screenY) {
        x = 75;
        y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;
        isShooting = false;

        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());

        bullets = new ArrayList<Bullet>();
        lastShootTime = System.currentTimeMillis();
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void setShooting(boolean shooting) {
        isShooting = shooting;
    }

    public boolean isShooting() {
        return isShooting;
    }

    public void update() {
        if (boosting) {
            speed += 2;
        } else {
            speed -= 5;
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }
        y -= speed + GRAVITY;
        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

        if (isShooting && System.currentTimeMillis() - lastShootTime > SHOOT_DELAY) {
            Bitmap bulletBitmap = BitmapFactory.decodeResource(bitmap.getResources(), R.drawable.bullet);
            Bullet bullet = new Bullet(bulletBitmap);
            bullet.setX(x + bitmap.getWidth() / 2);
            bullet.setY(y - bitmap.getHeight() / 2);
            bullets.add(bullet);
            lastShootTime = System.currentTimeMillis();
        }

        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    public void shoot() {
        Bullet bullet = new Bullet(x + bitmap.getWidth(), y + bitmap.getHeight() / 2);
        bullets.add(bullet);
    }

    public Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getWidth() {
        return bitmap.getWidth();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}