package com.crouniversity.fab;

import com.example.crouniversity.R;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class FloatingActionButton extends ImageButton {

	/**
	 * Constant representing normal size {@code 56dp}. Value: 0x0
	 */
	public static final int SIZE_NORMAL = 0;

	/**
	 * Constant representing mini size {@code 40dp}. Value: 0x1
	 */
	public static final int SIZE_MINI = 1;

	private int mSize;
	private int mColor;
	private ColorStateList mColorStateList;
	private boolean mShadow;
	private boolean mImplicitElevation;

	private boolean mUpdateLocked;

	private GradientDrawable mCircleDrawable;

	/**
	 * Gets abstract size of this button.
	 *
	 * @return {@link #SIZE_NORMAL} or {@link #SIZE_MINI}
	 */
	public int getSize() {
		return mSize;
	}

	/**
	 * Sets abstract size for this button.
	 * <p/>
	 * Xml attribute: {@code app:floatingActionButtonSize}
	 *
	 * @param size
	 *            {@link #SIZE_NORMAL} or {@link #SIZE_MINI}
	 */
	public void setSize(int size) {
		boolean changed = mSize != size;

		mSize = size;

		if (changed) {
			updateBackground();
		}
	}

	/**
	 * Gets background color of this button.
	 *
	 * @return color
	 */
	public int getColor() {
		return mColor;
	}

	/**
	 * Sets background color for this button.
	 * <p/>
	 * Xml attribute: {@code app:floatingActionButtonColor}
	 * <p/>
	 * NOTE: this method sets the <code>mColorStateList</code> field to
	 * <code>null</code>
	 *
	 * @param color
	 *            color
	 */
	public void setColor(int color) {
		boolean changed = mColor != color || mColorStateList != null;

		mColor = color;
		mColorStateList = null;

		if (changed) {
			updateBackground();
		}
	}

	public boolean isShadow() {
		return mShadow;
	}

	public void setShadow(boolean shadow) {
		boolean changed = mShadow != shadow;

		mShadow = shadow;

		if (changed) {
			updateBackground();
		}
	}

	public boolean isImplicitElevation() {
		return mImplicitElevation;
	}

	public void setImplicitElevation(boolean implicitElevation) {
		boolean changed = mImplicitElevation != implicitElevation;

		mImplicitElevation = implicitElevation;

		if (changed) {
			updateBackground();
		}
	}

	/**
	 * Gets color state list used as background for this button.
	 *
	 * @return may be null
	 */
	public ColorStateList getColorStateList() {
		return mColorStateList;
	}

	/**
	 * Sets color state list as background for this button.
	 * <p/>
	 * Xml attribute: {@code app:floatingActionButtonColor}
	 *
	 * @param colorStateList
	 *            color
	 */
	public void setColorStateList(ColorStateList colorStateList) {
		boolean changed = mColorStateList != colorStateList;

		mColorStateList = colorStateList;

		if (changed) {
			updateBackground();
		}
	}

	public void lockUpdate() {
		mUpdateLocked = true;
	}

	public void unlockUpdate() {
		mUpdateLocked = false;
	}

	public FloatingActionButton(Context context) {
		super(context);
		init(context, null, 0);
	}

	public FloatingActionButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs, R.attr.floatingActionButtonStyle);
	}

	public FloatingActionButton(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init(context, attrs, defStyle);
	}

	private void init(Context context, AttributeSet attrs, int defStyle) {
		TypedArray a;

		try {
			if (isInEditMode()) {
				return;
			}

			if (attrs == null) {
				return;
			}

			Resources.Theme theme = context.getTheme();
			if (theme == null) {
				return;
			}

			a = theme.obtainStyledAttributes(attrs,
					R.styleable.FloatingActionButton, defStyle,
					R.style.FloatingActionButton_Dark);
			if (a == null) {
				return;
			}
		} finally {
			mSize = SIZE_NORMAL;
			mColor = Color.GRAY;
			mColorStateList = null;
			mShadow = true;
			mImplicitElevation = true;
		}

		try {
			lockUpdate();
			initAttrs(a);
		} finally {
			unlockUpdate();
			a.recycle();
		}

		initBackground();
	}

	@SuppressLint("NewApi")
	private void initAttrs(TypedArray a) {
		setSize(a.getInteger(
				R.styleable.FloatingActionButton_floatingActionButtonSize,
				SIZE_NORMAL));
		setColor(a.getColor(
				R.styleable.FloatingActionButton_floatingActionButtonColor,
				Color.GRAY));
		setColorStateList(a
				.getColorStateList(R.styleable.FloatingActionButton_floatingActionButtonColor));
		setShadow(a.getBoolean(
				R.styleable.FloatingActionButton_floatingActionButtonShadow,
				true));
		setImplicitElevation(a
				.getBoolean(
						R.styleable.FloatingActionButton_floatingActionButtonImplicitElevation,
						true));

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			if (mImplicitElevation) {
				setElevation(getResources().getDimension(
						R.dimen.floating_action_button_elevation));
			}
		}
	}

	/**
	 * Inflate and initialize background drawable for this view with arguments
	 * inflated from xml or specified using {@link #setSize(int)} or
	 * {@link #setColor(int)}
	 * <p/>
	 * Invoked from constructor, but it's allowed to invoke this method manually
	 * from code.
	 */
	public void initBackground() {
		final int backgroundId;

		if (mSize == SIZE_MINI) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				backgroundId = R.drawable.com_shamanland_fab_circle_mini;
			} else {
				backgroundId = R.drawable.com_shamanland_fab_mini;
			}
		} else {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				backgroundId = R.drawable.com_shamanland_fab_circle_normal;
			} else {
				backgroundId = R.drawable.com_shamanland_fab_normal;
			}
		}

		updateBackground(getResources().getDrawable(backgroundId));
	}

	public void updateBackground() {
		if (!mUpdateLocked) {
			updateBackground(getBackground());
		}
	}

	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@SuppressLint("NewApi")
	private void updateBackground(Drawable background) {
		if (background instanceof LayerDrawable) {
			LayerDrawable layers = (LayerDrawable) background;
			if (layers.getNumberOfLayers() == 2) {
				Drawable shadow = layers.getDrawable(0);
				Drawable circle = layers.getDrawable(1);

				if (shadow instanceof GradientDrawable) {
					((GradientDrawable) shadow.mutate())
							.setGradientRadius(mShadow ? getShadowRadius(
									shadow, circle) : 0f);
				}

				if (circle instanceof GradientDrawable) {
					initCircleDrawable(circle);
				}
			}
		} else if (background instanceof GradientDrawable) {
			initCircleDrawable(background);
		}

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			if (mImplicitElevation) {
				setElevation(mShadow ? getResources().getDimension(
						R.dimen.floating_action_button_elevation) : 0f);
			}
		}

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
			// noinspection deprecation
			setBackgroundDrawable(background);
		} else {
			setBackground(background);
		}
	}

	private void initCircleDrawable(Drawable circle) {
		mCircleDrawable = (GradientDrawable) circle.mutate();
		mCircleDrawable.setColor(mColor);
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();

		if (mCircleDrawable != null && mColorStateList != null) {
			mCircleDrawable.setColor(mColorStateList.getColorForState(
					getDrawableState(), mColor));

			// NOTE maybe this line is required only for Gingerbread
			invalidate();
		}
	}

	/**
	 * Calculates required radius of shadow.
	 *
	 * @param shadow
	 *            underlay drawable
	 * @param circle
	 *            overlay drawable
	 * @return calculated radius, always >= 1
	 */
	protected static int getShadowRadius(Drawable shadow, Drawable circle) {
		int radius = 0;

		if (shadow != null && circle != null) {
			Rect rect = new Rect();
			radius = (circle.getIntrinsicWidth() + (shadow.getPadding(rect) ? rect.left
					+ rect.right
					: 0)) / 2;
		}

		return Math.max(1, radius);
	}
}
