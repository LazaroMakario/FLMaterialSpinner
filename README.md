FLMaterialSpinner
===================
Custom Spinner component with a floating label for Android.

----------

Screenshots
-------------
![](https://github.com/LazaroMakarioFelipe/FLMaterialSpinner/blob/master/screenshot/device-2017-08-22-140954.png?raw=true)

![](https://github.com/LazaroMakarioFelipe/FLMaterialSpinner/blob/master/screenshot/device-2017-10-02-171626.png?raw=true)

Usage
-------------

Add the spinner to your layout XML:

```xml
<com.lazaro.makario.flmaterialspinner.FLMaterialSpinner
    android:id="@+id/flmspinner_test"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    mflspinner:hintSpinner="SELECT AN OPTION"
    mflspinner:titleSpinner="Country">

  </com.lazaro.makario.flmaterialspinner.FLMaterialSpinner>
```

Add items to the spinner and listen for clicks:

```java
FLMaterialSpinner flMaterialSpinner = (FLMaterialSpinner) findViewById(R.id.flmspinner_test);

Map<String, String> mapFLMaterialSpinner = new LinkedHashMap<>();
        mapFLMaterialSpinner.put("MEX", "MEXICO");
        mapFLMaterialSpinner.put("AFG", "AFGHANISTAN");
        mapFLMaterialSpinner.put("BHS", "BAHAMAS");
        mapFLMaterialSpinner.put("KHM", "CAMBODIA");
        mapFLMaterialSpinner.put("COD", "DEMOCRATIC REPUBLIC OF THE CONGO");
        mapFLMaterialSpinner.put("EGY", "EGYPT");

        flMaterialSpinner.setMapAdapter(mapFLMaterialSpinner);
        
        flMaterialSpinner.setOnItemClickListener(new FLMaterialSpinnerInterface() {
            @Override
            public void setOnItemClickListener(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "setOnItemClickListener(): position = " + position, Toast.LENGTH_LONG).show();
            }
        });
```


If you need to set an error message:

![](https://github.com/LazaroMakarioFelipe/FLMaterialSpinner/blob/master/screenshot/device-2017-10-02-171556.png?raw=true)

you can validate selection with method `isValidSelected()` and set error with method `setError("error_message")`.


Attributes to customize the view:

|NAME|TYPE|HTML|
|-------------|-----------|----------------|
|`hintSpinner`|`string`|set title to spinner.|
|`titleSpinner`|`string`|set default text for first item.|

Methods to work with the view:

|Method|Description|
|------|-------------|
|`setSelectedItemKey("KEY_PARAMETER")`|select the item by Map key parameter.|
|`setSelectedItemValue("VALUE_PARAMETER")`|select the item by Map key parameter.|
|`setSelectedItemPosition()`|select the item by position|
|`getSelectedItemKey()`|return selected key.|
|`getSelectedItemKey()`|return selected key.|
|`getSelectedItemValue()`|return selected value.|
|`getSelectedItemPosition()`|return selected position.|
|`setSpinnerTitle()`|set title for spinner.|
|`clearAdapter()`|clear spinner items.|


Download
-------------

Download the latest AAR or grab via Gradle:

```
 compile 'com.lazaro.makario:FLMaterialSpinner:1.1.0'
```

License
--------

    Copyright (C) 2017 Macario Felipe

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
    
