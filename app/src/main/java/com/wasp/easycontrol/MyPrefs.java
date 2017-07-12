package com.wasp.easycontrol;

import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

import static org.androidannotations.annotations.sharedpreferences.SharedPref.Scope.UNIQUE;

/**
 * Created by wasp on 7/12/17.
 */

@SharedPref(value = UNIQUE)
public interface MyPrefs {

    @DefaultString("")
    String url();

}
