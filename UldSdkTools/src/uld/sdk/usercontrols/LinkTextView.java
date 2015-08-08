package uld.sdk.usercontrols;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 自定义Link
 */
public class LinkTextView extends TextView implements OnClickListener {

	private int defaultBackgroundColor = android.R.color.transparent;
	private int pressedBackgroundColor = android.R.color.tertiary_text_light;

	private int defaultTextColor = android.R.color.black;
	private int pressedTextColor = android.R.color.widget_edittext_dark;

	public LinkTextView(Context context) {
		super(context);
		setClickable(true);
		this.setOnClickListener((OnClickListener) this);
		getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	}

	public LinkTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setClickable(true);
		this.setOnClickListener((OnClickListener) this);
		getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
	}

	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		boolean bisPressed = false;
		int[] states = getDrawableState();
		for (int i = 0; i < states.length; ++i) {
			if (states[i] == android.R.attr.state_pressed) {
				bisPressed = true;
				break;
			}
		}
		if (bisPressed) {
			setTextColor(getResources().getColor(pressedTextColor));
			setBackgroundColor(getResources().getColor(pressedBackgroundColor));
		} else {
			setTextColor(getResources().getColor(defaultTextColor));
			setBackgroundColor(getResources().getColor(defaultBackgroundColor));
		}
	}

	@Override
	public void onClick(View v) {
		// Nothing TODO，在外部处理
	}

	/**
	 * 初始化背景颜色
	 * 
	 * @param defaultBackgroundColor
	 */
	public void initBackgroundColor(int defaultBackgroundColor) {
		this.defaultBackgroundColor = defaultBackgroundColor;
	}

	/**
	 * 初始化背景颜色
	 * 
	 * @param defaultBackgroundColor
	 * @param pressedBackgroundColor
	 */
	public void initBackgroundColor(int defaultBackgroundColor, int pressedBackgroundColor) {
		this.defaultBackgroundColor = defaultBackgroundColor;
		this.pressedBackgroundColor = pressedBackgroundColor;
	}

	/**
	 * 初始化字体颜色
	 * 
	 * @param defaultTextColor
	 * @param pressedTextColor
	 */
	public void initTextColorColor(int defaultTextColor) {
		this.defaultTextColor = defaultTextColor;
	}

	/**
	 * 初始化字体颜色
	 * 
	 * @param defaultTextColor
	 * @param pressedTextColor
	 */
	public void initTextColorColor(int defaultTextColor, int pressedTextColor) {
		this.defaultTextColor = defaultTextColor;
		this.pressedTextColor = pressedTextColor;
	}
}