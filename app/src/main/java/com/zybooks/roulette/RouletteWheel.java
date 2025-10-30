package com.zybooks.roulette;

import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import androidx.core.view.GestureDetectorCompat;
import android.view.GestureDetector;

public class RouletteWheel {

    private final ImageView wheelView;
    private final GestureDetectorCompat gestureDetector;

    public RouletteWheel(ImageView wheelView) {
        if (wheelView == null) {
            throw new IllegalArgumentException("wheelView cannot be null");
        }

        this.wheelView = wheelView;

        gestureDetector = new GestureDetectorCompat(wheelView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                // Log the velocities for debugging
                Log.d("RouletteWheel", "Fling detected! velocityX=" + velocityX + ", velocityY=" + velocityY);

                // Determine dominant direction
                float dominantVelocity;
                boolean clockwise;

                if (Math.abs(velocityX) > Math.abs(velocityY)) {
                    dominantVelocity = Math.abs(velocityX);
                    clockwise = velocityX > 0;
                } else {
                    dominantVelocity = Math.abs(velocityY);
                    clockwise = velocityY > 0;
                }

                // Ensure a minimum velocity so it always spins
                if (dominantVelocity < 200) {
                    dominantVelocity = 200;
                }

                spinWheel(dominantVelocity, clockwise);
                return true;
            }
        });

        // Attach listener after layout is ready
        wheelView.post(() -> wheelView.setOnTouchListener(
                (v, event) -> gestureDetector.onTouchEvent(event)
        ));
    }

    public void spinWheel(float velocity, boolean clockwise) {
        // Convert velocity to rotation
        float rotationAmount = velocity / 5f; // more sensitive
        if (!clockwise) {
            rotationAmount = -rotationAmount;
        }

        // Add multiple full rotations for realistic effect
        float targetAngle = rotationAmount + (clockwise ? 360 * 5 : -360 * 5);

        RotateAnimation rotate = new RotateAnimation(
                0,
                targetAngle,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f
        );
        rotate.setDuration(4000); // 4-second spin
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setFillAfter(true); // stay at final position

        wheelView.startAnimation(rotate);
    }
}
