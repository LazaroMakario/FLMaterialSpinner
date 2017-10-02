package com.lazaro.makario.flmaterialspinner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import java.util.LinkedHashMap;
import java.util.Map;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Makario, Felipe Làzaro on 19/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

public class FLMaterialSpinner extends LinearLayout {

  private FLMaterialSpinnerInterface flMaterialSpinnerInterface;

  private String defaultHint = getResources().getString(R.string.widget_spinner_hint);
  private String dafaultTitle = getResources().getString(R.string.widget_spinner_title);

  private TextInputLayout mflTextInputLayout;
  private FLMaterialSpinnerTextView flMaterialSpinnerTextView;
  private Rect hitRect;

  public FLMaterialSpinner(Context context) {
    super(context);
  }

  public FLMaterialSpinner(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    if (!isInEditMode()) {
      init(context, attrs);
    }
  }

  public FLMaterialSpinner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  /**
   * Init View with custom parameter
   * @param context
   * @param attrs
   */
  private void init(Context context, AttributeSet attrs) {

    View.inflate(context, R.layout.flmaterialspinner, this);

    mflTextInputLayout = (TextInputLayout) findViewById(R.id.textlay_mflspinner);
    flMaterialSpinnerTextView = (FLMaterialSpinnerTextView) findViewById(R.id.autotextv_mflspinner);

    /*hitRect = new Rect();

    setOnTouchListener(new OnTouchListener() {
      @Override
      public boolean onTouch(View view, MotionEvent motionEvent) {
        view.getHitRect(hitRect);
        if (hitRect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
          motionEvent.setLocation(0.0f, 0.0f);
          mflAutoCompleteTextView.dispatchTouchEvent(motionEvent);
        }
        return true;
      }
    });*/

    // Assign custom attributes
    if (attrs != null) {

      TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FLMaterialSpinner, 0, 0);

      try {
        this.defaultHint = a.getString(R.styleable.FLMaterialSpinner_hintSpinner);
        this.dafaultTitle = a.getString(R.styleable.FLMaterialSpinner_titleSpinner);
      } catch (Exception e) {
        Log.e("MFLCustomSpinner", "There was an error loading attributes.");
      } finally {
        a.recycle();
      }

    }

    flMaterialSpinnerTextView.setMapAdapter(new LinkedHashMap<String, String>(), this.defaultHint);
    flMaterialSpinnerTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_spinner_expand_black);

    mflTextInputLayout.setHintEnabled(true);
    mflTextInputLayout.setHint(this.dafaultTitle);

    flMaterialSpinnerTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setError(null);
        flMaterialSpinnerTextView.onItemClick(position);
        if( flMaterialSpinnerInterface != null ) flMaterialSpinnerInterface.setOnItemClickListener(parent, view, position, id);
      }
    });
  }

  public void setError(String message){
    mflTextInputLayout.setErrorEnabled( (message!=null) ? true : false );
    mflTextInputLayout.setError(message);
  }

  public int getSelectedItemPosition(){
    return flMaterialSpinnerTextView.getSelectedItemPosition();
  }

  public String getSelectedItemValue(){
    return flMaterialSpinnerTextView.getSelectedItemValue();
  }

  public String getSelectedItemKey(){
    return flMaterialSpinnerTextView.getSelectedItemKey();
  }

  public boolean isValidSelected(){
    return flMaterialSpinnerTextView.isValidSelected();
  }

  public void setOnItemClickListener(FLMaterialSpinnerInterface customSpinnerInterface){
    this.flMaterialSpinnerInterface = customSpinnerInterface;
  }

  public void setMapAdapter(Map<String,String> mapAdapter){
    setError(null);
    flMaterialSpinnerTextView.setMapAdapter(mapAdapter, this.defaultHint);
  }

  public void setSelectedItemKey(String atribValue){
    setError(null);
    flMaterialSpinnerTextView.setSelectedItemKey(atribValue);
  }

  public void setSelectedItemValue(String atribValue){
    setError(null);
    flMaterialSpinnerTextView.setSelectedItemValue(atribValue);
  }

  public void setSelectedItemPosition(int position){
    setError(null);
    flMaterialSpinnerTextView.setSelectedItemPosition(position);
  }

  public ListAdapter getAdapter(){
    return flMaterialSpinnerTextView.getAdapter();
  }

  public void setSpinnerTitle(String spinnerTitle){
    mflTextInputLayout.setHint(spinnerTitle);
  }

  public void clearAdapter(){
    setError(null);
    flMaterialSpinnerTextView.setMapAdapter(new LinkedHashMap<String, String>(), this.defaultHint);
  }
}
