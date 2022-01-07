package fr.lyceejulesfil.leschursdartichaut.ui.googlemaps;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GoogleMapsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public GoogleMapsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}