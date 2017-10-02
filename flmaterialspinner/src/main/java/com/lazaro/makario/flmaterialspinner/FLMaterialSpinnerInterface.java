package com.lazaro.makario.flmaterialspinner;

/*               ////
                (O O)
  --------oOO----(_)------------------------------
  Created by Makario, Felipe Làzaro on 20/09/2017.
  Email: lazaro.makario@gmail.com
  Complaint  Received, 10 year later...
  ----------------------oOO-----------------------*/

import android.view.View;
import android.widget.AdapterView;

public interface FLMaterialSpinnerInterface {
  void setOnItemClickListener(AdapterView<?> parent, View view, int position, long id);
}
