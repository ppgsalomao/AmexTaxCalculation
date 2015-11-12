AMEX Tax Calculator
======

Android App made as part of Recruitment proccess for Amex.

Assumptions
-----

 * All input must be provided on the following format: `[quantity] [name] at [unit_price]`
 * The name on the Receipt is exactly the name provided by the user (didn't move the word `imported` to the front)

Building and Running
-----

 * To build, just run `./gradlew assembleDebug`
 * If the application is not installed on the device, just run `adb install ./app/build/outputs/apk/app-debug.apk` from your `platform-tools/` directory.
 * If the application is already installed, run `adb install -r ./app/build/outputs/apk/app-debug.apk`

Libraries Used
---------

 * Support Libraries - http://developer.android.com/tools/support-library/index.html
 * ButterKnife - http://jakewharton.github.io/butterknife
 * Otto - http://square.github.io/otto/
 * JUnit - http://junit.org/
 * Mockito - http://mockito.org/
 * Robolectric - http://robolectric.org/

License
-------

    The MIT License (MIT)

    Copyright (c) 2015 Pedro Salomão

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
