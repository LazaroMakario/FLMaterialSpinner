package com.lazaro.makario.flmaterialspinner;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Làzaro, Makario Felipe on 18/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FLMaterialSpinnerAdapter extends ArrayAdapter<FLMaterialSpinnerItem> {

  private List<FLMaterialSpinnerItem> flMaterialSpinnerData = new ArrayList<>();
  private List<String> flMaterialSpinnerListKeys = new ArrayList<String>();
  private List<String> flMaterialSpinnerListValues = new ArrayList<String>();

  private Context flMaterialSpinnerContext;
  private int flMaterialSpinnerLayoutResourceID;

  private int flMaterialSpinnerDisabledTextColor = 0;
  private int flMaterialSpinnerActiveTextColor = 0;

  public FLMaterialSpinnerAdapter(Context mContext, int layoutResourceId, List<FLMaterialSpinnerItem> data) {

    super(mContext, layoutResourceId, data);

    this.flMaterialSpinnerLayoutResourceID = layoutResourceId;
    this.flMaterialSpinnerContext = mContext;
    this.flMaterialSpinnerData = data;

    this.flMaterialSpinnerDisabledTextColor = this.flMaterialSpinnerContext.getResources().getColor(R.color.disabled_spinner_text);
    this.flMaterialSpinnerActiveTextColor = this.flMaterialSpinnerContext.getResources().getColor(R.color.enabled_spinner_text);

    this.flMaterialSpinnerListKeys = new ArrayList<String>();
    this.flMaterialSpinnerListValues = new ArrayList<String>();

    for(FLMaterialSpinnerItem flMaterialSpinnerItem : data){
      this.flMaterialSpinnerListKeys.add(flMaterialSpinnerItem.getItemCode());
      this.flMaterialSpinnerListValues.add(flMaterialSpinnerItem.getItemValue());
    }
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    try{

      if(convertView == null){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(this.flMaterialSpinnerLayoutResourceID, parent, false);
      }

      FLMaterialSpinnerItem objectItem = this.flMaterialSpinnerData.get(position);

      TextView textViewItem = (TextView) convertView.findViewById(R.id.textView);
      textViewItem.setText(objectItem.getItemValue());

      textViewItem.setTextColor( (position==0) ?  this.flMaterialSpinnerDisabledTextColor : this.flMaterialSpinnerActiveTextColor);

    } catch (NullPointerException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }

    return convertView;
  }

  public List<FLMaterialSpinnerItem> getAllData(){
    return this.flMaterialSpinnerData;
  }

  public List<String> getFlMaterialSpinnerListKeys() {
    return this.flMaterialSpinnerListKeys;
  }

  public List<String> getFlMaterialSpinnerListValues() {
    return this.flMaterialSpinnerListValues;
  }
}
