package tr.com.abdullahkaracabey.app_error;

import android.app.Activity;
import android.widget.Toast;

import org.json.JSONObject;

import tr.com.abdullahkaracabey.apperror.R;

/**
 * AppError is an helper for show errors as toast.
 *
 * Created by Abdullah Karacabey on 24/03/15.
 */
public class AppError {
    public static final int ErrorCodeUnknownError =100;
    public static final int ErrorCodeConnectionNotAvaible =900;

    public static String JSON_ERROR_CODE_KEY ="error_code";
    public static String JSON_ERROR_DESCRIPTION_KEY ="error_description";

    private int code;
    private String description;
    private Activity activity;

    /**
     *
     * @param jsonError
     */

    public AppError(JSONObject jsonError){

        this.code=jsonError.optInt(JSON_ERROR_CODE_KEY);
        this.description=jsonError.optString(JSON_ERROR_DESCRIPTION_KEY);
    }

    public AppError(int code){
        this.code=code;

    }

    /**
     *
     * @param description
     */
    public AppError(String description){
        this.description=description;
    }

    /**
     * Shows error as toast.
     *
     * @param activity
     */
    public void showError(Activity activity){

        if (activity != null) {
            this.activity = activity;
        }

        try{
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    show();
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void show(){

        if(this.description==null)
            this.description=activity.getString(R.string.error_unknown);
        Toast.makeText(this.activity, this.description, Toast.LENGTH_LONG).show();
    }

    @Override
    public String toString() {
        return "Error: "+this.code+": "+this.description;
    }

    /**
     * Set error key for json object. Default is 'error_code'
     * @param JSON_ERROR_CODE_KEY
     */
    public static void setJSON_ERROR_CODE_KEY(String JSON_ERROR_CODE_KEY) {
        AppError.JSON_ERROR_CODE_KEY = JSON_ERROR_CODE_KEY;
    }

    /**
     * Set error description key for json object. Default is 'error_description'
     * @param JSON_ERROR_DESCRIPTION_KEY
     */
    public static void setJSON_ERROR_DESCRIPTION_KEY(String JSON_ERROR_DESCRIPTION_KEY) {
        AppError.JSON_ERROR_DESCRIPTION_KEY = JSON_ERROR_DESCRIPTION_KEY;
    }

    public static String getJSON_ERROR_CODE_KEY() {
        return JSON_ERROR_CODE_KEY;
    }

    public static String getJSON_ERROR_DESCRIPTION_KEY() {
        return JSON_ERROR_DESCRIPTION_KEY;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}