package com.fortech.breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private SurfaceHolder surfaceHolder;
    private volatile boolean playing;

    private Paint paint;
    private Rect paddle;
    private Rect ball;
    private int paddleWidth;
    private int paddleHeight;
    private int ballDimension;
    private int ballSpeedX;
    private int ballSpeedY;

    public GameView(Context context) {
        super(context);
        surfaceHolder = getHolder();
        paint = new Paint();
        paddle = new Rect();
        ball = new Rect();

        paddleWidth = 100;
        paddleHeight = 20;
        ballDimension = 20;
        ballSpeedX = 2;
        ballSpeedY = 2;
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
        }
    }

    private void update() {
        // 패들 움직임 처리
        // 사용자 입력 처리

        // 공 움직임 처리
        ball.left += ballSpeedX;
        ball.top += ballSpeedY;

        // 벽과 충돌 처리
        if (ball.left < 0 || ball.right > getWidth()) {
            ballSpeedX *= -1;
        }
        if (ball.top < 0 || ball.bottom > getHeight()) {
            ballSpeedY *= -1;
        }

        // 패들과 충돌 처리
        if (ball.bottom >= paddle.top && ball.bottom <= paddle.bottom
                && ball.left >= paddle.left && ball.right <= paddle.right) {
            ballSpeedY *= -1;
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            // 패들 그리기
            paint.setColor(Color.WHITE);
            paddle.left = getWidth() / 2 - paddleWidth / 2;
            paddle.top = getHeight() - paddleHeight - 10;
            paddle.right = paddle.left + paddleWidth;
            paddle.bottom = paddle.top + paddleHeight;
            canvas.drawRect(paddle, paint);

            // 공 그리기
            paint.setColor(Color.WHITE);
            ball.left = getWidth() / 2 - ballDimension / 2;
            ball.top = getHeight() / 2 - ballDimension / 2;
            ball.right = ball.left + ballDimension;
            ball.bottom = ball.top + ballDimension;
            canvas.drawRect(ball, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        playing = false;
    }
}
