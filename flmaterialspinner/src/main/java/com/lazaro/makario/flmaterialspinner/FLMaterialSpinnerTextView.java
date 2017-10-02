package com.lazaro.makario.flmaterialspinner;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Làzaro, Makario Felipe on 14/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("AppCompatCustomView")
public class FLMaterialSpinnerTextView extends AutoCompleteTextView {

  private static final int MAX_CLICK_DURATION = 200;
  private int flMaterialSpinnerPositionSelected = ListView.INVALID_POSITION;
  private boolean flMaterialSpinnerIsPopup;
  private long flMaterialSpinnerClickTime;

  public FLMaterialSpinnerTextView(Context context) {
    super(context);
  }

  public FLMaterialSpinnerTextView(Context arg0, AttributeSet arg1) {
    super(arg0, arg1);
  }

  public FLMaterialSpinnerTextView(Context arg0, AttributeSet arg1, int arg2) {
    super(arg0, arg1, arg2);
  }

  @Override
  public void showDropDown() {
    super.showDropDown();
    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_spinner_collapse_black);
  }

  @Override
  public void dismissDropDown() {
    super.dismissDropDown();
    setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_spinner_expand_black);
  }

  public void setCompoundDrawablesWithIntrinsicBounds(int drawable4Spinner){
    Drawable dropdownIcon = ContextCompat.getDrawable(getContext(), drawable4Spinner);
//    dropdownIcon.setColorFilter(new LightingColorFilter( Color.BLUE, Color.BLUE));
    if (dropdownIcon != null) {
      dropdownIcon.mutate().setAlpha(66);
      super.setCompoundDrawablesWithIntrinsicBounds(null, null, dropdownIcon, null);
    }
  }

  @Override
  protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    super.onFocusChanged(focused, direction, previouslyFocusedRect);
    if (focused) {
      InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(getWindowToken(), 0);
      setKeyListener(null);
      dismissDropDown();
    } else {
      flMaterialSpinnerIsPopup = false;
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {

    if (!isEnabled())
      return false;

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        flMaterialSpinnerClickTime = Calendar.getInstance().getTimeInMillis();
        break;
      }
      case MotionEvent.ACTION_UP: {
        long clickDuration = Calendar.getInstance().getTimeInMillis() - flMaterialSpinnerClickTime;
        if (clickDuration < MAX_CLICK_DURATION) {
          if (flMaterialSpinnerIsPopup) {
            dismissDropDown();
            flMaterialSpinnerIsPopup = false;
          } else {
            requestFocus();
            showDropDown();
            flMaterialSpinnerIsPopup = true;
          }
        }
      }
    }

    return super.onTouchEvent(event);
  }

  @Override
  protected void performFiltering(final CharSequence text, final int keyCode) {
    String filterText = "";
    super.performFiltering(filterText, keyCode);
  }

  @Override
  public <T extends ListAdapter & Filterable> void setAdapter(T adapter) {
    super.setAdapter(adapter);
  }

  /**
   * Set data for create adapter dropdown, component create default adapter with
   * default hint
   * @param mapAdapter
   * @param defaultHint
   */
  public void setMapAdapter(Map<String,String> mapAdapter, String defaultHint){

    Map<String,String> flMaterialSpinnerAdapter = new LinkedHashMap<>();
    flMaterialSpinnerAdapter.put("0",defaultHint);

    List<FLMaterialSpinnerItem> spinnerAdapterData = new ArrayList<FLMaterialSpinnerItem>();
    spinnerAdapterData.add(new FLMaterialSpinnerItem("0",defaultHint));

    for (Map.Entry<String,String> entry : mapAdapter.entrySet()) {
      spinnerAdapterData.add(new FLMaterialSpinnerItem(entry.getKey(),entry.getValue()));
      flMaterialSpinnerAdapter.put(entry.getKey(),entry.getValue());
    }

    setAdapter(new FLMaterialSpinnerAdapter(getContext(), R.layout.flmaterialspinner_item, spinnerAdapterData));

    setText( ((FLMaterialSpinnerItem) getAdapter().getItem(0)).getItemValue());
    setTextColor( getResources().getColor(R.color.disabled_spinner_text) );

    this.flMaterialSpinnerPositionSelected = ListView.INVALID_POSITION;
  }

  public void setItemSelected(int position){
    setText( ((FLMaterialSpinnerItem) getAdapter().getItem(position)).getItemValue());
    if(position == 0){
      setTextColor( getResources().getColor(R.color.disabled_spinner_text) );
      flMaterialSpinnerPositionSelected = ListView.INVALID_POSITION;
    }else{
      setTextColor( getResources().getColor(R.color.enabled_spinner_text) );
      flMaterialSpinnerPositionSelected = position;
    }
    dismissDropDown();
  }

  public int getSelectedItemPosition(){
    return flMaterialSpinnerPositionSelected;
  }

  public String getSelectedItemValue(){
    return (flMaterialSpinnerPositionSelected == ListView.INVALID_POSITION) ? ((FLMaterialSpinnerItem) getAdapter().getItem(0)).getItemValue() : ((FLMaterialSpinnerItem) getAdapter().getItem(flMaterialSpinnerPositionSelected)).getItemValue();
  }

  public String getSelectedItemKey(){
    return (flMaterialSpinnerPositionSelected == ListView.INVALID_POSITION) ? ((FLMaterialSpinnerItem) getAdapter().getItem(0)).getItemCode() : ((FLMaterialSpinnerItem) getAdapter().getItem(flMaterialSpinnerPositionSelected)).getItemCode();
  }

  public boolean isValidSelected(){
    return (flMaterialSpinnerPositionSelected == ListView.INVALID_POSITION) ? false : true;
  }

  public void setSelectedItemKey(String atribValue) {
    int position = ((FLMaterialSpinnerAdapter) getAdapter()).getFlMaterialSpinnerListKeys().indexOf(atribValue);
    setItemSelected( (position == ListView.INVALID_POSITION) ? 0 : position );
  }

  public void setSelectedItemValue(String atribValue) {
    int position = ((FLMaterialSpinnerAdapter) getAdapter()).getFlMaterialSpinnerListValues().indexOf(atribValue);
    setItemSelected( (position == ListView.INVALID_POSITION) ? 0 : position );
  }

  public void setSelectedItemPosition(int position) {
    if( position > ListView.INVALID_POSITION && position < getAdapter().getCount() ){
      setItemSelected(position);
    }
  }

  public void onItemClick(int position){
    setItemSelected(position);
    flMaterialSpinnerIsPopup = false;
  }
}
