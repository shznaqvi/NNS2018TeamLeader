package edu.aku.hassannaqvi.nns2018_teamleadersapp.validation;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.aku.hassannaqvi.nns2018_teamleadersapp.core.MainApp;

/**
 * Created by ali.azaz on 12/04/17.
 */

public abstract class validatorClass {

    public static boolean EmptyTextBox(Context context, EditText txt, String msg) {
        if (TextUtils.isEmpty(txt.getText().toString().trim())) {
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(empty): " + msg, Toast.LENGTH_SHORT).show();
            }
            txt.setError("This data is Required! ");    // Set Error on last radio button
//            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": This data is Required!");
            return false;
        } else {
            txt.setError(null);
            txt.clearFocus();
            return true;
        }
    }

    public static boolean RangeTextBox(Context context, EditText txt, int min, int max, String msg, String type) {

        if (Integer.valueOf(txt.getText().toString().trim()) < min || Integer.valueOf(txt.getText().toString().trim()) > max) {
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
            }
            txt.setError("Range is " + min + " to " + max + type + " ... ");    // Set Error on last radio button
//            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Range is " + min + " to " + max + " times...  ");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean RangeTextBox(Context context, EditText txt, int min, int max, int defaultVal, String msg, String type) {

        if (Integer.valueOf(txt.getText().toString()) != defaultVal) {
            if ((Integer.valueOf(txt.getText().toString().trim()) < min || Integer.valueOf(txt.getText().toString().trim()) > max)) {
                if (MainApp.validateFlag) {
                    Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
                }
                txt.setError("Range is " + min + " to " + max + " or " + defaultVal + type + " ... ");    // Set Error on last radio button
//                txt.requestFocus();
                Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Range is " + min + " to " + max + " or " + defaultVal + " ...  ");
                return false;
            } else {
                txt.setError(null);
                return true;
            }
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean RangeTextBox(Context context, EditText txt, double min, double max, String msg, String type) {

        if (Double.valueOf(txt.getText().toString().trim()) < min || Double.valueOf(txt.getText().toString().trim()) > max) {
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
            }
            txt.setError("Range is " + min + " to " + max + type + " ... ");    // Set Error on last radio button
//            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": Range is " + min + " to " + max + " times...  ");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean RangeTextBoxforDate(Context context, EditText txt, int min, int max, int defaultVal, String msg) {

        if (Integer.valueOf(txt.getText().toString()) != defaultVal) {
            if ((Integer.valueOf(txt.getText().toString()) < min || Integer.valueOf(txt.getText().toString()) > max)) {
                if (MainApp.validateFlag) {
                    Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
                }
                txt.setError(msg);    // Set Error on last radio button
//                txt.requestFocus();
                Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": " + msg + " " + min + " to " + max + " or " + defaultVal + " ...  ");
                return false;
            } else {
                txt.setError(null);
                return true;
            }
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean RangeTextBoxforDate(Context context, EditText txt, double min, double max, String msg) {

        if (Integer.valueOf(txt.getText().toString()) < min || Integer.valueOf(txt.getText().toString()) > max) {
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(invalid): " + msg, Toast.LENGTH_SHORT).show();
            }
            txt.setError(msg);    // Set Error on last radio button
//            txt.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(txt.getId()) + ": " + msg + min + " to " + max + " times...  ");
            return false;
        } else {
            txt.setError(null);
            return true;
        }
    }

    public static boolean EmptySpinner(Context context, Spinner spin, String msg) {
        if (spin.getSelectedItem() == "....") {
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            }
            ((TextView) spin.getSelectedView()).setText("This Data is Required");
            ((TextView) spin.getSelectedView()).setTextColor(Color.RED);
//            spin.requestFocus();
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(spin.getId()) + ": This data is Required!");
            return false;
        } else {
            ((TextView) spin.getSelectedView()).setError(null);
            return true;
        }
    }

    public static boolean EmptyRadioButton(Context context, RadioGroup rdGrp, final RadioButton rdBtn, String msg) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {

            /*ScrollView scrollView = null;

            Activity myact = (Activity) context;
            final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) myact.findViewById(android.R.id.content)).getChildAt(0);

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof ScrollView) {
                    scrollView = (ScrollView) viewGroup.getChildAt(i);
                    break;
                }
            }

            final ScrollView myScrollView = scrollView;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int ypos = findYPositionInView(myScrollView, rdBtn, 0);
                    myScrollView.smoothScrollTo(0, ypos - 200);

                    rdBtn.setError("This data is Required!");
                }
            }, 200);
*/
            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            }

            rdBtn.setError("This data is Required!");    // Set Error on last radio button

            //rdBtn.setFocusable(true);
            //rdBtn.setFocusableInTouchMode(true);
            //rdBtn.requestFocus();

            /*rdBtn.clearFocus();

             rdGrp.requestFocus();
            rdGrp.requestFocusFromTouch();
            rdGrp.clearFocus();
            rdGrp.clearChildFocus(rdBtn);
            */
            //((LinearLayout)rdGrp.getParent()).scrollTo(rdGrp.getScrollX(), rdGrp.getScrollY());
            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(rdGrp.getId()) + ": This data is Required!");
            return false;
        } else {
            rdBtn.setError(null);
            return true;
        }
    }

    private static int findYPositionInView(View rootView, View targetView, int yCumulative) {
        if (rootView == targetView)
            return yCumulative;

        if (rootView instanceof ViewGroup) {
            ViewGroup parentView = (ViewGroup) rootView;
            for (int i = 0; i < parentView.getChildCount(); i++) {
                View child = parentView.getChildAt(i);
                int yChild = yCumulative + (int) child.getY();

                int yNested = findYPositionInView(child, targetView, yChild);
                if (yNested != -1)
                    return yNested;
            }
        }

        return -1; // not found
    }

    public static boolean EmptyRadioButton(Context context, RadioGroup rdGrp, final RadioButton rdBtn, EditText txt, final String msg) {
        if (rdGrp.getCheckedRadioButtonId() == -1) {

            /*ScrollView scrollView = null;

            Activity myact = (Activity) context;
            final ViewGroup viewGroup = (ViewGroup) ((ViewGroup) myact.findViewById(android.R.id.content)).getChildAt(0);

            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                if (viewGroup.getChildAt(i) instanceof ScrollView) {
                    scrollView = (ScrollView) viewGroup.getChildAt(i);
                    break;
                }
            }

            final ScrollView myScrollView = scrollView;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    int ypos = findYPositionInView(myScrollView, rdBtn, 0);
                    myScrollView.smoothScrollTo(0, ypos - 200);

                    rdBtn.setError("This data is Required!");
                }
            }, 200);
*/

            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            }
            //rdBtn.setError("This data is Required!");    // Set Error on last radio button

            //rdBtn.setFocusable(true);
            //rdBtn.setFocusableInTouchMode(true);
            //rdGrp.requestFocus();

            /*rdGrp.requestFocusFromTouch();
            rdGrp.clearFocus();
            rdGrp.clearChildFocus(rdBtn);*/
            //((LinearLayout)rdGrp.getParent()).scrollTo(rdGrp.getScrollX(), rdGrp.getScrollY());

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(rdGrp.getId()) + ": This data is Required!");
            return false;
        } else {
            rdBtn.setError(null);
            if (rdBtn.isChecked()) {
                return EmptyTextBox(context, txt, msg);
            } else {
                txt.setError(null);
                txt.clearFocus();
                return true;
            }
        }
    }

    public static boolean EmptyCheckBox(Context context, LinearLayout container, CheckBox cbx, String msg) {

        Boolean flag = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                if (cb.isChecked()) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            cbx.setError(null);
            return true;
        } else {

            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            }
            cbx.setError("This data is Required!");    // Set Error on last radio button

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(cbx.getId()) + ": This data is Required!");
            return false;
        }
    }

    public static boolean EmptyCheckBox(Context context, LinearLayout container, CheckBox cbx, EditText txt, String msg) {

        Boolean flag = false;
        for (int i = 0; i < container.getChildCount(); i++) {
            View v = container.getChildAt(i);
            if (v instanceof CheckBox) {
                CheckBox cb = (CheckBox) v;
                if (cb.isChecked()) {
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            cbx.setError(null);
            if (cbx.isChecked()) {
                return EmptyTextBox(context, txt, msg);
            } else {
                txt.setError(null);
                txt.clearFocus();
                return true;
            }
        } else {

            if (MainApp.validateFlag) {
                Toast.makeText(context, "ERROR(Empty)" + msg, Toast.LENGTH_SHORT).show();
            }
            cbx.setError("This data is Required!");    // Set Error on last radio button

            Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(cbx.getId()) + ": This data is Required!");
            return false;
        }
    }

    public static void setErrorOnMultTextFields(Context context, String msg, Boolean condition, EditText... textFields) {
        Boolean firstIterationFlag = true;
        for (EditText textField : textFields) {
            if (condition) {

                if (MainApp.validateFlag) {
                    Toast.makeText(context, "ERROR(MultipleTxt): " + msg, Toast.LENGTH_SHORT).show();
                }

                textField.setError(msg);
                if (firstIterationFlag) {
                    textField.requestFocus();
                    firstIterationFlag = false;
                } else {
                }
                Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(textField.getId()) + msg);

            } else {
                textField.setError(null);

            }
        }

    }

    public static void setErrorOnMultRadioFields(Context context, String msg, Boolean condition, RadioButton... Buttons) {
        for (RadioButton button : Buttons) {
            if (condition) {
                button.setError(msg);
                Log.i(context.getClass().getName(), context.getResources().getResourceEntryName(button.getId()) + msg);

            } else {
                button.setError(null);

            }
        }

    }
}

