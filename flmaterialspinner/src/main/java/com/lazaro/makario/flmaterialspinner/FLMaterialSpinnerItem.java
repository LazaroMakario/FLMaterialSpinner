package com.lazaro.makario.flmaterialspinner;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Làzaro, Makario Felipe on 18/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

public class FLMaterialSpinnerItem {

  private String itemCode;
  private String itemValue;

  FLMaterialSpinnerItem(String code, String value){
    this.itemCode = code;
    this.itemValue = value;
  }

  public String getItemCode() {
    return itemCode;
  }

  public void setItemCode(String itemCode) {
    this.itemCode = itemCode;
  }

  public String getItemValue() {
    return itemValue;
  }

  public void setItemValue(String itemValue) {
    this.itemValue = itemValue;
  }
}
